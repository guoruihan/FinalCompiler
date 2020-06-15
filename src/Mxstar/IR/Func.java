package Mxstar.IR;

import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.PhysicalReg;
import Mxstar.IR.Operand.Register;
import Mxstar.IR.Operand.VirtualReg;
import Mxstar.Symbol.*;

import java.util.HashSet;
import java.util.LinkedList;

public class Func {
    public enum Type {
        External, Library, UserDefined
    }

    public Type type;
    public String name;
    public boolean hasReturnValue;
    public boolean hasOutput;
    public BB enterBB;
    public BB leaveBB;
    public LinkedList<BB> basicblocks;
    public LinkedList<BB> reversePostOrder;
    public LinkedList<BB> reversePostOrderOnCFG;
    public LinkedList<VirtualReg> parameters;

    public HashSet<VarSymbol> Global;
    public HashSet<VarSymbol> recursiveGlobal;
    public HashSet<PhysicalReg> usedPhysicalRegister;
    public HashSet<PhysicalReg> recursiveUsedPhysicalRegister;

    public HashSet<Func> callee;

    public HashSet<BB> visitedBB;
    public HashSet<Func> visitedFunc;


    public Func(Type type, String name, boolean HasReturnValue) {
        this.type = type;
        this.name = name;
        this.hasReturnValue = HasReturnValue;
        this.hasOutput = false;
        this.basicblocks = new LinkedList<>();
        this.reversePostOrder = new LinkedList<>();
        this.reversePostOrderOnCFG = new LinkedList<>();
        this.parameters = new LinkedList<>();
        this.Global = new HashSet<>();
        this.recursiveGlobal = new HashSet<>();
        this.usedPhysicalRegister = new HashSet<>();
        this.recursiveUsedPhysicalRegister = new HashSet<>();
        this.callee = new HashSet<>();
        this.visitedBB = new HashSet<>();
        this.visitedFunc = new HashSet<>();

        if (type != Type.UserDefined && !name.equals("initabcd")) {
            for (PhysicalReg pr : Regs.allRegs) {
                if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("gp") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra")||pr.name.equals("t4"))
                    continue;
                this.usedPhysicalRegister.add(pr);
                this.recursiveUsedPhysicalRegister.add(pr);
            }
        }
    }

    public Func(String name, boolean HasReturnValue) {
        this.type = Type.Library;
        this.name = name;
        this.hasReturnValue = HasReturnValue;
        this.hasOutput = false;
        this.basicblocks = new LinkedList<>();
        this.reversePostOrder = new LinkedList<>();
        this.reversePostOrderOnCFG = new LinkedList<>();
        this.parameters = new LinkedList<>();
        this.Global = new HashSet<>();
        this.recursiveGlobal = new HashSet<>();
        this.usedPhysicalRegister = new HashSet<>();
        this.recursiveUsedPhysicalRegister = new HashSet<>();
        this.callee = new HashSet<>();
        this.visitedBB = new HashSet<>();
        this.visitedFunc = new HashSet<>();

        if (!name.equals("init")) {
            for (PhysicalReg pr : Regs.allRegs) {
                if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra") )
                    continue;
                this.usedPhysicalRegister.add(pr);
                this.recursiveUsedPhysicalRegister.add(pr);
            }
        }
    }

    public void dfsReversePostOrder(BB node) {
        if (visitedBB.contains(node))
            return ;
        visitedBB.add(node);
        for (BB bb: node.successers)
            dfsReversePostOrder(bb);
        reversePostOrder.addFirst(node);
    }

    public void dfsReversePostOrderOnReversedCFG(BB node) {
        if (visitedBB.contains(node))
            return ;
        visitedBB.add(node);
        for (BB bb: node.frontiers)
            dfsReversePostOrderOnReversedCFG(bb);
        reversePostOrderOnCFG.addFirst(node);
    }

    public void dfsRecursiveUsedGlobalVariables(Func node) {
        if (visitedFunc.contains(node))
            return ;
        visitedFunc.add(node);
        for (Func func: node.callee)
            dfsRecursiveUsedGlobalVariables(func);
        recursiveGlobal.addAll(node.Global);
    }

    public void finishBuild() {
        for (BB bb: basicblocks) {
            bb.successers.clear();
            bb.frontiers.clear();
        }
        int tot = 0;
        for (BB bb: basicblocks) {
            if (bb.tail instanceof Cjump) {
                tot++;
                bb.successers.add(((Cjump)bb.tail).thenblock);
                bb.successers.add(((Cjump)bb.tail).elseblock);

            } else if (bb.tail instanceof Jump) {
                bb.successers.add(((Jump)bb.tail).toBB);
            }
            for (BB suc: bb.successers) {
                suc.frontiers.add(bb);
            }
        }
        //System.err.println(tot);
        for (BB bb: basicblocks) {
            if (bb.tail instanceof Cjump) {
                Cjump cjump = (Cjump)bb.tail;
                if (cjump.thenblock.frontiers.size() < cjump.elseblock.frontiers.size()) {
                    cjump.op = cjump.getNegOp();
                    BB temp = cjump.thenblock;
                    cjump.thenblock = cjump.elseblock;
                    cjump.elseblock = temp;
                }
            }
        }//把大的那块放到前面（是优化
        visitedBB.clear();
        reversePostOrder.clear();
        dfsReversePostOrder(enterBB);

        visitedBB.clear();
        reversePostOrderOnCFG.clear();
        dfsReversePostOrderOnReversedCFG(leaveBB);

        visitedFunc.clear();
        recursiveGlobal.clear();
        dfsRecursiveUsedGlobalVariables(this);
        //我用不到
    }

    public LinkedList<PhysicalReg> trans(LinkedList<Register> regs) {
        LinkedList<PhysicalReg> ret = new LinkedList<>();
        for (Register reg: regs) {
            ret.add((PhysicalReg) reg);
        }
        return ret;
    }

    public void dfsRecursiveUsedPhysicalRegisters(Func node) {
        if (visitedFunc.contains(node))
            return ;
        visitedFunc.add(node);
        for (Func func: node.callee)
            dfsRecursiveUsedPhysicalRegisters(func);
        recursiveUsedPhysicalRegister.addAll(node.usedPhysicalRegister);
    }

    public boolean isSpecialBinaryaryOp(BinaryInst.BinaryOp op) {
        return op == BinaryInst.BinaryOp.MUL || op == BinaryInst.BinaryOp.DIV || op == BinaryInst.BinaryOp.REM;
    }

    public void finishAllocate() {
        for (BB bb: basicblocks) {
            for (Inst inst = bb.head; inst != null;  inst = inst.next) {
                if (inst instanceof Return)
                    return;
                if (inst instanceof Call) {
                    usedPhysicalRegister.addAll(Regs.callerSave);
                } else if (inst instanceof BinaryInst && isSpecialBinaryaryOp(((BinaryInst)inst).op)) {
                    usedPhysicalRegister.add(Regs.a0);
                    usedPhysicalRegister.add(Regs.t5);
                    if (((BinaryInst)inst).val instanceof Register)
                        usedPhysicalRegister.add((PhysicalReg)(((BinaryInst)inst).val));
                } else /*if (inst instanceof Setcc) {
                    usedPhysicalRegister.add(Regs.a0);
                    if (((Setcc)inst).val1 instanceof Register)
                        usedPhysicalRegister.add((PhysicalReg)(((Setcc)inst).val1));
                    if (((Setcc)inst).val2 instanceof Register)
                        usedPhysicalRegister.add((PhysicalReg)(((Setcc)inst).val2));
                    if (((Setcc)inst).tpos instanceof Register)
                        usedPhysicalRegister.add((PhysicalReg)(((Setcc)inst).tpos));
                } else */{
                    usedPhysicalRegister.addAll(trans(inst.getUseRegs()));
                    usedPhysicalRegister.addAll(trans(inst.getDefRegs()));
                }
            }
        }
        visitedFunc.clear();
        dfsRecursiveUsedPhysicalRegisters(this);
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
