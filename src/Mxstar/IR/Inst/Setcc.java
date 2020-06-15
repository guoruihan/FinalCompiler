package Mxstar.IR.Inst;

import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;

import static Mxstar.IR.Regs.*;

public class Setcc extends Inst {
    public Cjump.CompareOP op;
    public Operand val1;
    public Operand val2;
    public Address tpos;

    public Cjump.CompareOP getReverseOp() {
        switch (op) {
            case GE:
                return Cjump.CompareOP.LT;
            case GT:
                return Cjump.CompareOP.LE;
            case EQ:
                return Cjump.CompareOP.EQ;
            case LT:
                return Cjump.CompareOP.GE;
            case LE:
                return Cjump.CompareOP.GT;
            case NE:
                return Cjump.CompareOP.NE;
            default:
                assert false;
                return Cjump.CompareOP.EQ;
        }
    }

  /*  public Setcc(BB bb, Address tpos, Operand val1, Cjump.CompareOP op, Operand val2) {
        super(bb);
        this.tpos = tpos;
        this.val1 = val1;
        this.op = op;
        this.val2 = val2;
    }*/

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
        if (!regs.contains(vt3)) {
            regs.add(vt3);
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
        return null;
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof Register)
            regs.add((Register) tpos);
        return regs;
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap){
        if (tpos instanceof Register && renameMap.containsKey(tpos)) {
            tpos = renameMap.get(tpos);
        }
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(val1, val2);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
