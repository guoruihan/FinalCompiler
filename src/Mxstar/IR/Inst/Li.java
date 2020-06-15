package Mxstar.IR.Inst;

import Mxstar.IR.BB;
import Mxstar.IR.IRVisitor;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Li extends Inst {
    public Register tpos;
    public AlloSpace val;

    public Li(BB bb, Register tpos, AlloSpace val) {
        super(bb);
        this.tpos = tpos;
        this.val = val;
    }
    @Override
    public Inst copy(BB bb) {
        return new Li(bb, tpos, val);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        val = val.copy();
        val.renameUseRegs(renameMap);
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (renameMap.containsKey(tpos))
            tpos = renameMap.get(tpos);
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>(val.getUseRegs());
        regs.add(tpos);
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