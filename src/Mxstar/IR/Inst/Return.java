package Mxstar.IR.Inst;

import Mxstar.IR.*;

import java.util.HashMap;
import java.util.LinkedList;
import Mxstar.IR.Operand.*;

public class Return extends Inst {
    public Return(BB bb) {
        super(bb);
    }

    @Override
    public Inst copy(BB bb) {
        return new Return(bb);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {}

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {}

    @Override
    public LinkedList<Register> getDefRegs() {
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs =  new LinkedList<>();
        if (BelongBB.BelongFunc.hasReturnValue)
            regs.add(Regs.va0);
        return regs;
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return new LinkedList<>();
    }
    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
