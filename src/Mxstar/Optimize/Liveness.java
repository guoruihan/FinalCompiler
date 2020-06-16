package Mxstar.Optimize;


import Mxstar.IR.BB;
import Mxstar.IR.Func;
import Mxstar.IR.IRProgram;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.Register;
import Mxstar.IR.Operand.*;

import javax.naming.LinkLoopException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Liveness {
    private HashMap<BB, HashSet<VirtualReg>> liveOut;
    private HashMap<BB, HashSet<VirtualReg>> ueVar;
    private HashMap<BB, HashSet<VirtualReg>> varKill;



    public LinkedList<VirtualReg> turnVir(Collection<Register> regs) {
        LinkedList<VirtualReg> ret = new LinkedList<>();
        for (Register reg: regs) {
            ret.add((VirtualReg)reg);
        }
        return ret;
    }

    LinkedList<Register> gettmpUse(Inst inst, boolean aftertag){
        if ((!(inst instanceof Call)) || aftertag) {
            return inst.getUseRegs();
        } else {
            return ((Call) inst).getCallUsed();
        }
    }

    private void initBB(BB bb, boolean aftertag) {
        HashSet<VirtualReg> localVarKill = new HashSet<>();
        HashSet<VirtualReg> localUEVar = new HashSet<>();

        for (Inst inst = bb.head; inst != null; inst = inst.next) {
            LinkedList<Register> tmpUse = gettmpUse(inst,aftertag);
            for (VirtualReg reg: turnVir(tmpUse)) {
                if (!localVarKill.contains(reg)) {
                    localUEVar.add(reg);
                }
            }
            localVarKill.addAll(turnVir(inst.getDefRegs()));
        }
        ueVar.put(bb, localUEVar);
        varKill.put(bb, localVarKill);
    }

    void initfunc (Func func) {
        ueVar = new HashMap<>();
        varKill = new HashMap<>();
        liveOut = new HashMap<>();

        for (BB bb: func.basicblocks) {
            ueVar.put(bb, new HashSet<>());
            varKill.put(bb, new HashSet<>());
            liveOut.put(bb, new HashSet<>());
        }
    }
    public void getLiveTag(Func func, boolean aftertag) {
        initfunc(func);

        for (BB bb: func.basicblocks) {
            initBB(bb, aftertag);
        }

        boolean tag = true;
        while(tag) {
            tag = false;
            for (BB bb: func.reverseOnCFG) {
                int oldval = liveOut.get(bb).size();
                for (BB succ: bb.successers) {
                    HashSet<VirtualReg> regs = new HashSet<>(liveOut.get(succ));

                    regs.removeAll(varKill.get(succ));
                    regs.addAll(ueVar.get(succ));
                    //process it!
                    liveOut.get(bb).addAll(regs);
                }
                if (liveOut.get(bb).size() != oldval)
                    tag = true;
            }
        }
    }

    public void calcUDCnt(Func func) {
        HashSet<VirtualReg> alreadyUD = new HashSet<>();
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {

                LinkedList<VirtualReg> virRegs = turnVir(inst.getUseRegs());
                virRegs.addAll(turnVir(inst.getDefRegs()));
                for (VirtualReg reg: virRegs) {

                    if (!alreadyUD.contains(reg)) {
                        alreadyUD.add(reg);
                        reg.cntUD = 0;
                    }
                    reg.cntUD = reg.cntUD + 1;
                }


            }
        }

    }

    void putin(Graph graph, Inst inst){
        graph.addRegisters(turnVir(inst.getUseRegs()));
        graph.addRegisters(turnVir(inst.getDefRegs()));
    }

    void makeedge(Func func, Graph graph){
        for (BB bb: func.basicblocks) {
            HashSet<VirtualReg> liveNow = new HashSet<>(liveOut.get(bb));
            for (Inst inst = bb.tail; inst != null; inst = inst.prev ) {

                for (VirtualReg reg1: turnVir(inst.getDefRegs())) {

                    for (VirtualReg reg2: liveNow) {

                        graph.addEdge(reg1, reg2);
                    }
                }
                liveNow.removeAll(turnVir(inst.getDefRegs()));
                liveNow.addAll(turnVir(inst.getUseRegs()));
            }
        }
    }

    public void getInterferenceGraph(Func func, Graph graph) {
        getLiveTag(func, true);

        graph.clear();;
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                putin(graph,inst);
            }
        }

        makeedge(func, graph);

    }
}
