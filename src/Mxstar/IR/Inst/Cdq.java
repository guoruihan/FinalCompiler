package Mxstar.IR.Inst;

import Mxstar.IR.*;

import Mxstar.IR.Operand.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Cdq extends Inst {

    public Cdq(BB bb) {
        super(bb);
    }

    @Override
    public Inst copy(BB bb) {
        return new Cdq(bb);
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
        return new LinkedList<>();
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
