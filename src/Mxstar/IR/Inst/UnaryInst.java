package Mxstar.IR.Inst;

import Mxstar.IR.*;

import java.util.HashMap;
import java.util.LinkedList;
import Mxstar.IR.Operand.*;

public class UnaryInst extends  Inst {

    public enum UnaryOp{
        NEG, NOT, INC, DEC, LOGICNEG
    }

    public UnaryOp op;
    public Address tpos;

    public UnaryInst(BB bb, UnaryOp op, Address tpos) {
        super(bb);
        this.op = op;
        this.tpos = tpos;
    }

    @Override
    public Inst copy(BB bb) {
        return new UnaryInst(bb, op, tpos);
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof AlloSpace)
            regs.addAll(((AlloSpace)tpos).getUseRegs());
        else if (tpos instanceof Register)
            regs.add((Register) tpos);
        return regs;
    }

    @Override
    public  void renameUseReg(HashMap<Register, Register> renameMap) {
        if (tpos instanceof AlloSpace) {
            tpos = ((AlloSpace)tpos).copy();
            ((AlloSpace)tpos).renameUseRegs(renameMap);
        } else if (tpos instanceof Register && renameMap.containsKey(tpos)) {
            tpos = renameMap.get(tpos);
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
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(tpos);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
