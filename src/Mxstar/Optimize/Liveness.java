package Mxstar.Optimize;


import Mxstar.IR.BB;
import Mxstar.IR.Func;
import Mxstar.IR.IRProgram;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.Register;
import Mxstar.IR.Operand.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Liveness {
    private HashMap<BB, HashSet<VirtualReg>> liveOut;
    private HashMap<BB, HashSet<VirtualReg>> ueVar;
    private HashMap<BB, HashSet<VirtualReg>> varKill;


    void prepare (Func func) {
        liveOut = new HashMap<>();
        ueVar = new HashMap<>();
        varKill = new HashMap<>();

        for (BB bb: func.basicblocks) {
            liveOut.put(bb, new HashSet<>());
            ueVar.put(bb, new HashSet<>());
            varKill.put(bb, new HashSet<>());
        }
    }

    public LinkedList<VirtualReg> tranVir(Collection<Register> regs) {
        LinkedList<VirtualReg> ret = new LinkedList<>();
        for (Register reg: regs) {
            ret.add((VirtualReg)reg);
        }
        return ret;
    }

    private void init(BB bb, boolean afterAllocation) {
        HashSet<VirtualReg> localUEVar = new HashSet<>();
        HashSet<VirtualReg> localVarKill = new HashSet<>();

        for (Inst inst = bb.head; inst != null; inst = inst.next) {
            LinkedList<Register> tmpUse;
            if (inst instanceof Call && !afterAllocation) {
                tmpUse = ((Call) inst).getCallUsed();
            } else {
                tmpUse = inst.getUseRegs();
            }
            for (VirtualReg reg: tranVir(tmpUse)) {
                if (!localVarKill.contains(reg)) {
                    localUEVar.add(reg);
                }
            }
            localVarKill.addAll(tranVir(inst.getDefRegs()));
        }
        ueVar.put(bb, localUEVar);
        varKill.put(bb, localVarKill);
    }

    public void calcLiveOut(Func func, boolean afterAllocation) {
        prepare(func);

        for (BB bb: func.basicblocks) {
            init(bb, afterAllocation);
        }

        boolean changed = true;
        while(changed) {
            changed = false;
            for (BB bb: func.reversePostOrderOnCFG) {
                int oldSize = liveOut.get(bb).size();
                for (BB succ: bb.successers) {
                    HashSet<VirtualReg> regs = new HashSet<>(liveOut.get(succ));
                    regs.removeAll(varKill.get(succ));
                    regs.addAll(ueVar.get(succ));
                    liveOut.get(bb).addAll(regs);
                }
                if (liveOut.get(bb).size() != oldSize)
                    changed = true;
            }
        }
    }

    public HashMap<BB, HashSet<VirtualReg>> getLiveOut(Func func) {
        calcLiveOut(func, false);
        return liveOut;
    }

    public void calcUDCnt(Func func) {
        HashSet<VirtualReg> alreadyUD = new HashSet<>();
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                LinkedList<VirtualReg> virRegs = tranVir(inst.getUseRegs());
                virRegs.addAll(tranVir(inst.getDefRegs()));
                for (VirtualReg reg: virRegs) {
                    if (alreadyUD.contains(reg)) {
                        reg.cntUD = reg.cntUD + 1;
                    } else {
                        alreadyUD.add(reg);
                        reg.cntUD = 1;
                    }
                }
            }
        }

    }


    public void getInterferenceGraph(Func func, Graph graph) {
        calcLiveOut(func, true);

        graph.clear();;
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                graph.addRegisters(tranVir(inst.getUseRegs()));
                graph.addRegisters(tranVir(inst.getDefRegs()));
            }
        }

        for (BB bb: func.basicblocks) {
            HashSet<VirtualReg> liveNow = new HashSet<>(liveOut.get(bb));
            for (Inst inst = bb.tail; inst != null; inst = inst.prev ) {
                for (VirtualReg reg1: tranVir(inst.getDefRegs())) {
                    for (VirtualReg reg2: liveNow) {
                        graph.addEdge(reg1, reg2);
                    }
                }
                liveNow.removeAll(tranVir(inst.getDefRegs()));
                liveNow.addAll(tranVir(inst.getUseRegs()));
            }
        }
    }
}
