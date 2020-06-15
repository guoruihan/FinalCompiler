package Mxstar.IR.Inst;


import Mxstar.IR.BB;
import Mxstar.IR.IRVisitor;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;


public class Instack extends Inst {
    public Operand val;

    public Instack(BB bb, Operand val) {
        super(bb);
        this.val = val;
    }

    @Override
    public Inst copy(BB bb) {
        return new Instack(bb, val);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (val instanceof AlloSpace) {
            val = ((AlloSpace) val).copy();
            ((AlloSpace)val).renameUseRegs(renameMap);
        }
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (val instanceof Register && renameMap.containsKey(val)) {
            val = renameMap.get(val);
        }
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (val instanceof Register)
            regs.add(((Register)val));

        return regs;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (val instanceof AlloSpace)
            regs.addAll(((AlloSpace)val).getUseRegs());

        return regs;
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(val);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}