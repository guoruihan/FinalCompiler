package Mxstar.IR.Inst;

import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Move extends Inst {
    public Address tpos;
    public Operand val;

    public Move(BB bb, Address tpos, Operand val) {
        super(bb);
        this.tpos = tpos;
        this.val = val;
    }

    @Override
    public Inst copy(BB bb) {
        return new Move(bb, tpos, val);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (tpos instanceof AlloSpace) {
            tpos = ((AlloSpace) tpos).copy();
            ((AlloSpace)tpos).renameUseRegs(renameMap);
        }
        if (val instanceof AlloSpace) {
            val = ((AlloSpace) val).copy();
            ((AlloSpace)val).renameUseRegs(renameMap);
        }
        if (val instanceof Register && renameMap.containsKey(val)) {
            val = renameMap.get(val);
        }
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (tpos instanceof Register && renameMap.containsKey(tpos))
            tpos = renameMap.get(tpos);
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof Register)
            regs.add((Register) tpos);
        return regs;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof AlloSpace)
            regs.addAll(((AlloSpace)tpos).getUseRegs());
        if (val instanceof AlloSpace)
            regs.addAll(((AlloSpace)val).getUseRegs());
        if (val instanceof Register)
            regs.add((Register) val);
        return regs;
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(val,tpos);
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
