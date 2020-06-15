package Mxstar.IR.Inst;

import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Outstack extends Inst{
    public Operand tpos;

    public Outstack(BB bb, Operand tpos) {
        super(bb);
        this.tpos = tpos;
    }

    @Override
    public Inst copy(BB bb) {
        return new Outstack(bb, tpos);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (tpos instanceof AlloSpace) {
            tpos = ((AlloSpace) tpos).copy();
            ((AlloSpace)tpos).renameUseRegs(renameMap);
        }
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (tpos instanceof Register && renameMap.containsKey(tpos)) {
            tpos = renameMap.get(tpos);
        }
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof Register)
            regs.add(((Register)tpos));

        return regs;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof AlloSpace)
            regs.addAll(((AlloSpace)tpos).getUseRegs());

        return regs;
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(tpos);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
