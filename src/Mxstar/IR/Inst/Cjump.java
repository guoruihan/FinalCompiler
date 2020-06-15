package Mxstar.IR.Inst;


import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;

import Mxstar.IR.Operand.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Cjump extends Inst {
    public enum CompareOP {
        EQ, NE, GT, GE, LT, LE
    }

    public CompareOP op;
    public BB thenblock;
    public BB elseblock;
    public Operand val1;
    public Operand val2;

    public Cjump(BB bb, Operand val1, CompareOP op, Operand val2, BB thenbb, BB elsebb) {
        super(bb);
        //System.err.println(val1+" "+((Imm)val2).value+" "+op);
        this.val1 = val1;
        this.op = op;
        this.val2 = val2;
        this.thenblock = thenbb;
        this.elseblock = elsebb;
    }
    public BB doCompare() {
        int v1 = ((Imm)val1).value;
        int v2 = ((Imm)val2).value;
        boolean r;
        switch (op) {
            case NE:
                r = v1 != v2;
                break;
            case EQ:
                r = v1 == v2;
                break;
            case LE:
                r = v1 <= v2;
                break;
            case LT:
                r = v1 < v2;
                break;
            case GT:
                r = v1 > v2;
                break;
            case GE:
                r = v1 >= v2;
                break;
            default:
                r = false;
                break;
        }
        return r ? thenblock : elseblock;
    }

    public CompareOP getNegOp() {
        switch (op) {
            case GE:
                return CompareOP.LT;
            case GT:
                return CompareOP.LE;
            case EQ:
                return CompareOP.NE;
            case LT:
                return CompareOP.GE;
            case LE:
                return CompareOP.GT;
            case NE:
                return CompareOP.EQ;
            default:
                assert false;
                return CompareOP.EQ;
        }
    }

    public CompareOP getReverseOp() {
        switch (op) {
            case GE:
                return CompareOP.LT;
            case GT:
                return CompareOP.LE;
            case EQ:
                return CompareOP.EQ;
            case LT:
                return CompareOP.GE;
            case LE:
                return CompareOP.GT;
            case NE:
                return CompareOP.NE;
            default:
                assert false;
                return CompareOP.EQ;
        }
    }


    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        LinkedList<Operand> srcs = new LinkedList<>();
        srcs.add(val1);
        srcs.add(val2);
        for (Operand src : srcs) {
            if (src instanceof AlloSpace)
                regs.addAll(((AlloSpace)src).getUseRegs());
            else if (src instanceof Register) {
                regs.add((Register)src);
            }
        }
        return regs;
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (val1 instanceof AlloSpace) {
            val1 = ((AlloSpace)(val1)).copy();
            ((AlloSpace)(val1)).renameUseRegs(renameMap);
        } else if (val1 instanceof Register && renameMap.containsKey(val1)) {
            val1 = renameMap.get(val1);
        }
        if (val2 instanceof AlloSpace) {
            val2 = ((AlloSpace) val2).copy();
            ((AlloSpace)val2).renameUseRegs(renameMap);
        } else if (val2 instanceof Register && renameMap.containsKey(val2)) {
            val2 = renameMap.get(val2);
        }
    }

    @Override
    public Inst copy(BB bb) {
        return new Cjump(bb, val1, op, val2, thenblock, elseblock);
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        return new LinkedList<>();
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap){}

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(val1, val2);
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
