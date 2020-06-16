package Mxstar.Optimize;

import Mxstar.Configuration;
import Mxstar.IR.*;
import Mxstar.IR.Operand.*;
import Mxstar.IR.IRProgram;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;
import Mxstar.IR.Operand.Stack;
import Mxstar.IR.Regs;

import java.util.*;

public class GraphAllo {
    public IRProgram irProgram;
    public PrintIR irPrinter;
    public LinkedList<PhysicalReg> allRegs;
    public int Maxcolor;

    void init_allo(IRProgram irProgram){
        this.irPrinter = new PrintIR();
        this.irProgram = irProgram;
        allRegs = new LinkedList<>();
    }

    public GraphAllo(IRProgram irProgram) {
        init_allo(irProgram);
        for (PhysicalReg pr : Regs.allRegs) {
            //if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra")||pr.name.equals("t4"))
            if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra") || pr.name.equals("gp")||pr.name.equals("t4") )
                continue;
            //caller save 的存储有很大问题啊
            allRegs.add(pr);
        }
        Maxcolor = allRegs.size();

        for (Func func: irProgram.funcs) {
            processFunc(func);
        }
    }

    public LinkedList<VirtualReg> tranVir(Collection<Register> regs) {
        LinkedList<VirtualReg> ret = new LinkedList<>();
        for (Register reg: regs) {
            ret.add((VirtualReg)reg);
        }
        return ret;
    }

    private HashSet<VirtualReg> alloRegs;
    private HashSet<VirtualReg> simplifyList;
    private Graph graph;
    private HashSet<VirtualReg> alloList;
    private LinkedList<VirtualReg> selectStack;
    private HashMap<VirtualReg, PhysicalReg> color;


    private void init() {
        initofinit();
        for (VirtualReg virReg: graph.getAllRegs()) {
            if (graph.getDeg(virReg) < Maxcolor) {
                simplifyList.add(virReg);
            } else {
                alloList.add(virReg);
            }
        }
    }

    void initofinit(){
        simplifyList = new HashSet<>();
        alloList = new HashSet<>();
        alloRegs = new HashSet<>();
        selectStack = new LinkedList<>();
        color = new HashMap<>();
    }

    void simplify(VirtualReg reg){
        graph.delReg(reg);
        simplifyList.remove(reg);
    }

    void simplifysmall(VirtualReg reg){
        alloList.remove(reg);
        simplifyList.add(reg);
    }

    private void simplify() {
        VirtualReg reg = simplifyList.iterator().next();
        LinkedList<VirtualReg> neighbors = new LinkedList<>(graph.getNeighbor(reg));
        simplify(reg);
        for (VirtualReg virReg: neighbors) {
            if (graph.getDeg(virReg) < Maxcolor && alloList.contains(virReg)) {
                simplifysmall(virReg);
            }
        }
        selectStack.addFirst(reg);
    }

    int inf = 1000000000;

    private void Allo() {
        VirtualReg candidate = null;
        double mxRank = -2;
        for (VirtualReg reg: alloList) {
            double rank = 1.0 * graph.getDeg(reg) / reg.cntUD  ;
            if (reg.allocatedPhyReg != null) {
                rank = -1;
            }
            if (reg.spillPlace != null && !Configuration.doGlobalAllocate) {
                rank = inf;
            }

            if (rank > mxRank) {
                mxRank = rank;
                candidate = reg;
            }
        }
        graph.delReg(candidate);
        alloList.remove(candidate);
        selectStack.addFirst(candidate);
    }

    private Func func;
    private Graph initialGraph;
    private void assignColor() {
        for (VirtualReg virReg: selectStack) {
            if (virReg.allocatedPhyReg == null)
                continue;
            color.put(virReg, virReg.allocatedPhyReg);
        }
        for (VirtualReg virReg: selectStack) {
            if (virReg.allocatedPhyReg != null)
                continue;

            if (!Configuration.doGlobalAllocate && virReg.spillPlace != null) {
                alloRegs.add(virReg);
            }

            HashSet<PhysicalReg> okColors = new HashSet<>(allRegs);

            for (VirtualReg neighbor : initialGraph.getNeighbor(virReg)) {
                if (color.containsKey(neighbor)) {
                    okColors.remove(color.get(neighbor));
                }
            }
            if (!okColors.isEmpty()) {
                PhysicalReg phyReg = null;
                for (PhysicalReg reg : Regs.callerSave) {
                    boolean jmptag = false;
                    if (okColors.contains(reg)) {
                        phyReg = reg;
                        jmptag = true;
                    }
                    if(jmptag)
                        break;
                }
                if (phyReg == null)
                    phyReg = okColors.iterator().next();
                color.put(virReg, phyReg);
            } else {
                alloRegs.add(virReg);
            }

        }
    }

    private void rewriteProgram() {
        HashMap<VirtualReg, AlloSpace> alloPlaces = new HashMap<>();
        for (VirtualReg virReg: alloRegs) {
            if (virReg.spillPlace != null) {
                alloPlaces.put(virReg, virReg.spillPlace);
            } else {
                alloPlaces.put(virReg, new Stack(virReg.name));
            }
        }
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                dorewrite(inst,bb,alloPlaces);
            }
        }
    }

    void dorewrite(Inst inst,BB nbb,HashMap<VirtualReg, AlloSpace> alloPlaces){

        LinkedList<VirtualReg> used = tranVir(inst.getUseRegs());
        LinkedList<VirtualReg> defined = tranVir(inst.getDefRegs());
        HashMap<Register, Register> renameMap = new HashMap<>();
        used.retainAll(alloRegs);
        for (VirtualReg virReg: used) {
            if (!renameMap.containsKey(virReg)) {
                renameMap.put(virReg, new VirtualReg(""));
            }
        }
        defined.retainAll(alloRegs);
        for (VirtualReg virReg: defined) {
            if (!renameMap.containsKey(virReg)) {
                renameMap.put(virReg, new VirtualReg(""));
            }
        }
        inst.renameUseReg(renameMap);
        inst.renameDefReg(renameMap);
        for (VirtualReg virReg: used) {
            inst.prepend(new Move(nbb, renameMap.get(virReg), alloPlaces.get(virReg)));
        }
        for (VirtualReg virReg: defined) {
            inst.append(new Move(nbb, alloPlaces.get(virReg), renameMap.get(virReg)));
            inst = inst.next;
        }
    }

    public static Liveness liveness= new Liveness();
    private void rewriteRegs() {
        HashMap<Register, Register> tmpMap = new HashMap<>();
        for (HashMap.Entry<VirtualReg, PhysicalReg> entry: color.entrySet()) {
            tmpMap.put(entry.getKey(), entry.getValue());
        }
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                inst.renameDefReg(tmpMap);
                inst.renameUseReg(tmpMap);
            }
        }
    }

    private void processFunc (Func func) {
        this.func = func;
        initialGraph = new Graph();
        for(;;){
            liveness.getInterferenceGraph(func, initialGraph);
            liveness.calcUDCnt(func);

            graph = new Graph(initialGraph);
            init();
            while(true) {
                if (!simplifyList.isEmpty()) {
                    simplify();
                    continue;
                }
                if (!alloList.isEmpty()) {
                    Allo();
                    continue;
                }
                break;
            }
            assignColor();

            if (!alloRegs.isEmpty()) {
                rewriteProgram();
            } else {
                rewriteRegs();
                break;
            }
        }


        func.finishAllocate();
    }

}
