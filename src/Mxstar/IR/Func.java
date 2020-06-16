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
    public BB enterBB;
    public BB leaveBB;
    public String name;
    public LinkedList<BB> basicblocks;
    public LinkedList<BB> reversePostOrder;
    public LinkedList<BB> reverseOnCFG;

    public HashSet<PhysicalReg> usedPhysicalRegister;
    public HashSet<PhysicalReg> recursiveUsedPhysicalRegister;

    public HashSet<Func> callee;
    public HashSet<Func> caller;//userless
    public HashSet<Func> visitedFunc;
    public HashSet<BB> visitedBB;


    public LinkedList<VirtualReg> parameters;
    public HashSet<VarSymbol> Global;
    public HashSet<VarSymbol> recursiveGlobal;

    public boolean hasReturnValue;
    public boolean hasOutput;

    public Func(Type type, String name, boolean HasReturnValue) {
        this.type = type;
        this.name = name;
        this.hasReturnValue = HasReturnValue;
        this.hasOutput = false;
        this.basicblocks = new LinkedList<>();
        this.reversePostOrder = new LinkedList<>();
        this.reverseOnCFG = new LinkedList<>();
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
                if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("gp") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra")||pr.name.equals("t4")|| pr.name.equals("gp"))
                    continue;
                this.recursiveUsedPhysicalRegister.add(pr);
                this.usedPhysicalRegister.add(pr);
            }
        }
    }

    public Func(String name, boolean HasReturnValue) {
        this.usedPhysicalRegister = new HashSet<>();
        this.parameters = new LinkedList<>();
        this.hasReturnValue = HasReturnValue;
        this.hasOutput = false;
        this.Global = new HashSet<>();
        this.recursiveGlobal = new HashSet<>();
        this.recursiveUsedPhysicalRegister = new HashSet<>();
        this.callee = new HashSet<>();
        this.visitedBB = new HashSet<>();
        this.type = Type.Library;
        this.basicblocks = new LinkedList<>();
        this.reversePostOrder = new LinkedList<>();
        this.reverseOnCFG = new LinkedList<>();
        this.name = name;
        this.visitedFunc = new HashSet<>();

        if (!name.equals("init")) {
            for (PhysicalReg pr : Regs.allRegs) {
                if (pr.name.equals("zero") || pr.name.equals("sp") || pr.name.equals("s0") || pr.name.equals("tp") || pr.name.equals("t0") || pr.name.equals("t1") || pr.name.equals("t2") || pr.name.equals("ra") || pr.name.equals("gp")||pr.name.equals("t4"))
                    continue;
                this.usedPhysicalRegister.add(pr);
                this.recursiveUsedPhysicalRegister.add(pr);
            }
        }
    }

    public void dfsRev(BB node) {
        if (visitedBB.contains(node))
            return ;
        visitedBB.add(node);
        for (BB bb: node.successers)
            dfsRev(bb);
        reversePostOrder.addFirst(node);
    }

    public void dfsRevOnRevCFG(BB node) {
        if (visitedBB.contains(node))
            return ;
        visitedBB.add(node);
        for (BB bb: node.frontiers)
            dfsRevOnRevCFG(bb);
        reverseOnCFG.addFirst(node);
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
        dfsRev(enterBB);

        visitedBB.clear();
        reverseOnCFG.clear();
        dfsRevOnRevCFG(leaveBB);

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

    boolean isCall(Inst inst){
        return inst instanceof Call;
    }
    boolean isBin(Inst inst){
        return inst instanceof BinaryInst;
    }

    public void finishAllocate() {
        boolean rettag = false;
        for (BB bb: basicblocks) {
            for (Inst inst = bb.head; inst != null;  inst = inst.next) {
                if (inst instanceof Return)
                    rettag = true;
                if(rettag)
                    return;
                if (isCall(inst)) {
                    usedPhysicalRegister.addAll(Regs.callerSave);
                } else if (!(isBin(inst) && isSpecialBinaryaryOp(((BinaryInst)inst).op))) {
                    usedPhysicalRegister.addAll(trans(inst.getUseRegs()));
                    usedPhysicalRegister.addAll(trans(inst.getDefRegs()));
                } else {
                    usedPhysicalRegister.add(Regs.a0);
                    usedPhysicalRegister.add(Regs.t5);
                    if (((BinaryInst)inst).val instanceof Register)
                        usedPhysicalRegister.add((PhysicalReg)(((BinaryInst)inst).val));
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
