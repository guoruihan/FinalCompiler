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

    boolean checkAllo(Operand pos){
        return pos instanceof AlloSpace;
    }

    boolean checkReg(Operand pos){
        return pos instanceof Register;
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (checkAllo(tpos)) {
            tpos = ((AlloSpace) tpos).copy();
            ((AlloSpace)tpos).renameUseRegs(renameMap);
        } else if (checkReg(tpos)) {
            if(renameMap.containsKey((Register)tpos)) {
                tpos = renameMap.get((Register)tpos);
            }
        }

        if (checkAllo(val)) {
            val = ((AlloSpace)(val)).copy();
            ((AlloSpace)val).renameUseRegs(renameMap);
        } else if (checkReg(val)) {
            if(renameMap.containsKey((Register)val)) {
                val = renameMap.get((Register)val);
            }
        }

    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {

        if (checkReg(tpos)) {
            if(renameMap.containsKey((Register)tpos)) {
                tpos = renameMap.get((Register)tpos);
            }
        }

    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs;
        regs = new LinkedList<>();
        if (checkAllo(tpos)) {
            regs.addAll(((AlloSpace)tpos).getUseRegs());
        } else if (checkReg(tpos)) {
            regs.add((Register) tpos);
        }
        if (checkAllo(val)) {
            regs.addAll(((AlloSpace)val).getUseRegs());
        } else if (checkReg(val)) {
            regs.add((Register) val);
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
