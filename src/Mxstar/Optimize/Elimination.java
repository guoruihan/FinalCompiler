package Mxstar.Optimize;

import Mxstar.IR.BB;
import Mxstar.IR.Func;
import Mxstar.IR.IRProgram;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Elimination {
    public IRProgram irProgram;
    public Liveness livenessAnalyzer;
    public HashMap<BB, HashSet<VirtualReg>> liveOut;

    public Elimination(IRProgram irProgram) {
        this.irProgram = irProgram;
        this.livenessAnalyzer = new Liveness();
    }

    public LinkedList<VirtualReg> tranVir(Collection<Register> regs) {
        LinkedList<VirtualReg> ret = new LinkedList<>();
        for (Register reg: regs) {
            ret.add((VirtualReg)reg);
        }
        return ret;
    }

    private boolean canEliminate(Inst inst) {
        return inst instanceof BinaryInst || inst instanceof UnaryInst || inst instanceof Move || inst instanceof Li;
    }

    private void processFunc(Func func) {
        liveOut = livenessAnalyzer.getLiveOut(func);
        for (BB bb: func.basicblocks) {
            HashSet<VirtualReg> liveSet = new HashSet<>(liveOut.get(bb));
            for (Inst inst = bb.tail; inst != null; inst = inst.prev) {
                LinkedList<Register> used = inst instanceof Call ? ((Call) inst).getCallUsed() : inst.getUseRegs();
                LinkedList<Register> defined = inst.getDefRegs();
                boolean dead = true;
                if (defined.isEmpty()) {
                    dead = false;
                }
                for (Register reg: defined) {
                    if (liveSet.contains(reg) || ((VirtualReg)reg).spillPlace != null) {
                        dead = false;
                        break;
                    }
                }
                if (dead && canEliminate(inst)) {
                    inst.remove();
                } else {
                    liveSet.removeAll(tranVir(defined));
                    liveSet.addAll(tranVir(used));
                }
            }

        }
    }

    public void run() {
        for (Func func: irProgram.funcs) {
            processFunc(func);
        }
    }


}
