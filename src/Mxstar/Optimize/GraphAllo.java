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
    public static Liveness livenessanalyse = new Liveness();
    public LinkedList<PhysicalReg> generalRegisters;
    public int K;

    public GraphAllo(IRProgram irProgram) {
        this.irPrinter = new PrintIR();
        this.irProgram = irProgram;
        generalRegisters = new LinkedList<>();
        for (PhysicalReg pr : Regs.allRegs) {
            if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra")||pr.name.equals("t4"))
            /*if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra") ||
                    pr.name.equals("a0") ||pr.name.equals("a1") ||pr.name.equals("a2") ||pr.name.equals("a3") ||pr.name.equals("a4") ||pr.name.equals("a5") ||pr.name.equals("a6")||pr.name.equals("a7")
                    || pr.name.equals("t3")|| pr.name.equals("t4")|| pr.name.equals("t5")|| pr.name.equals("x5")|| pr.name.equals("x6")|| pr.name.equals("x7"))*/
                continue;
            //caller save 的存储有很大问题啊
            generalRegisters.add(pr);
        }
        K = generalRegisters.size();

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

    private Func func;
    private Graph originGraph;
    private Graph graph;
    private HashSet<VirtualReg> simplifyList;
    private HashSet<VirtualReg> spillList;
    private HashSet<VirtualReg> spillRegs;
    private LinkedList<VirtualReg> selectStack;
    private HashMap<VirtualReg, PhysicalReg> color;

    private void init() {
        simplifyList = new HashSet<>();
        spillList = new HashSet<>();
        spillRegs = new HashSet<>();
        selectStack = new LinkedList<>();
        color = new HashMap<>();
        for (VirtualReg virReg: graph.getAllRegs()) {
            if (graph.getDeg(virReg) < K) {
                simplifyList.add(virReg);
            } else {
                spillList.add(virReg);
            }
        }
    }

    private void simplify() {
        VirtualReg reg = simplifyList.iterator().next();
        LinkedList<VirtualReg> neighbors = new LinkedList<>(graph.getAdjacent(reg));
        graph.delReg(reg);
        simplifyList.remove(reg);
        for (VirtualReg virReg: neighbors) {
            if (graph.getDeg(virReg) < K && spillList.contains(virReg)) {
                spillList.remove(virReg);
                simplifyList.add(virReg);
            }
        }
        selectStack.addFirst(reg);
    }

    private void spill() {
        VirtualReg candidate = null;
        double mxRank = -2;
        for (VirtualReg reg: spillList) {
            double rank = 1.0 * graph.getDeg(reg) / reg.cntUD  ;
            if (reg.allocatedPhyReg != null) {
                rank = -1;
            }
            if (reg.spillPlace != null && !Configuration.doGlobalAllocate) {
                rank = 1e50;
            }

            if (rank > mxRank) {
                mxRank = rank;
                candidate = reg;
            }
        }
        graph.delReg(candidate);
        spillList.remove(candidate);
        selectStack.addFirst(candidate);
    }

    private void assignColor() {
        for (VirtualReg virReg: selectStack) {
            if (virReg.allocatedPhyReg != null)
                color.put(virReg, virReg.allocatedPhyReg);
        }
        for (VirtualReg virReg: selectStack) {
            if (virReg.allocatedPhyReg != null)
                continue;
            if (!Configuration.doGlobalAllocate && virReg.spillPlace != null) {
                spillRegs.add(virReg);
            }

            HashSet<PhysicalReg> okColors = new HashSet<>(generalRegisters);
//            if (originGraph == null) {
//                System.out.println("is origin graph a idiot");
//            }
            for (VirtualReg neighbor: originGraph.getAdjacent(virReg)) {
                if (color.containsKey(neighbor)) {
                    okColors.remove(color.get(neighbor));
                }
            }
            if (okColors.isEmpty()) {
                spillRegs.add(virReg);
            } else {
                PhysicalReg phyReg  = null;
                for (PhysicalReg reg: Regs.callerSave) {
                    if (okColors.contains(reg)) {
                        phyReg = reg;
                        break;
                    }
                }
                if (phyReg == null)
                    phyReg = okColors.iterator().next();
                color.put(virReg, phyReg);
            }
        }
    }

    private void rewriteProgram() {
        HashMap<VirtualReg, AlloSpace> spillPlaces = new HashMap<>();
        for (VirtualReg virReg: spillRegs) {
            if (virReg.spillPlace != null) {
                spillPlaces.put(virReg, virReg.spillPlace);
            } else {
                spillPlaces.put(virReg, new Stack(virReg.name));
            }
        }
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                LinkedList<VirtualReg> used = tranVir(inst.getUseRegs());
                LinkedList<VirtualReg> defined = tranVir(inst.getDefRegs());
                HashMap<Register, Register> renameMap = new HashMap<>();
                used.retainAll(spillRegs);
                defined.retainAll(spillRegs);
                for (VirtualReg virReg: used) {
                    if (!renameMap.containsKey(virReg)) {
                        renameMap.put(virReg, new VirtualReg(""));
                    }
                }
                for (VirtualReg virReg: defined) {
                    if (!renameMap.containsKey(virReg)) {
                        renameMap.put(virReg, new VirtualReg(""));
                    }
                }
                inst.renameUseReg(renameMap);
                inst.renameDefReg(renameMap);
                for (VirtualReg virReg: used) {
                    inst.prepend(new Move(bb, renameMap.get(virReg), spillPlaces.get(virReg)));
                }
                for (VirtualReg virReg: defined) {
                    inst.append(new Move(bb, spillPlaces.get(virReg), renameMap.get(virReg)));
                    inst = inst.next;
                }
            }
        }
    }

    private void rewriteRegs() {
        HashMap<Register, Register> tmpMap = new HashMap<>();
        for (HashMap.Entry<VirtualReg, PhysicalReg> entry: color.entrySet()) {
            tmpMap.put(entry.getKey(), entry.getValue());
        }
//        System.out.println(func.name + tmpMap.size());
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                inst.renameDefReg(tmpMap);
                inst.renameUseReg(tmpMap);
            }
        }
    }

    private void processFunc (Func func) {
        this.func = func;
        originGraph = new Graph();
        while(true) {
            livenessanalyse.getInterferenceGraph(func, originGraph);
            livenessanalyse.calcUDCnt(func);

            graph = new Graph(originGraph);
            init();
            while(true) {
                if (!simplifyList.isEmpty())
                    simplify();
                else if (!spillList.isEmpty())
                    spill();
                else
                    break;
            }
//            if (originGraph == null) {
//                System.out.println();
//            }
            assignColor();

            if (!spillRegs.isEmpty()) {
                rewriteProgram();
            } else {
                rewriteRegs();
                break;
            }
        }

        /*
        System.err.println("===============================================");
        System.err.println("IR for debug: after " + func.name);
        PrintIR irPrinter = new PrintIR();
        irPrinter.visit(irProgram);
        irPrinter.printTo(System.err);
*/

        func.finishAllocate();
    }

}
