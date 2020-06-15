package Mxstar.IR.Inst;
import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;
import static Mxstar.IR.Regs.*;
public class BinaryInst extends Inst {
    public enum BinaryOp{
        ADD, SUB, MUL, DIV, REM, SLL, SRL, AND, OR, XOR
    }//REM是%
    public BinaryOp op;
    public Address tpos;
    public Operand val;

    public BinaryInst(BB belong, BinaryOp op, Address tpos, Operand val){
        super(belong);
        this.op = op;
        this.tpos = tpos;
        this.val = val;
    }


    @Override
    public Inst copy(BB bb) {
        return new BinaryInst(bb, op, tpos, val);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (val instanceof AlloSpace) {
            val = ((AlloSpace)(val)).copy();
            ((AlloSpace)(val)).renameUseRegs(renameMap);
        } else if (val instanceof Register && renameMap.containsKey(val)) {
            val = renameMap.get(val);
        }

        if (tpos instanceof AlloSpace) {
            tpos = ((AlloSpace) tpos).copy();
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
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (val instanceof AlloSpace) {
            regs.addAll(((AlloSpace)val).getUseRegs());
        } else if (val instanceof Register) {
            regs.add((Register) val);
        }
        if (tpos instanceof AlloSpace) {
            regs.addAll(((AlloSpace)tpos).getUseRegs());
        } else if (tpos instanceof Register) {
            regs.add((Register) tpos);
        }
        return regs;
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        return defualtGetSlot(val, tpos);
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (tpos instanceof Register)
            regs.add((Register) tpos);
        return regs;
    }
    
    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
