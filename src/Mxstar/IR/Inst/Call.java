package Mxstar.IR.Inst;

import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.IR.Operand.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Integer.min;

public class Call extends Inst {

    public Address tpos;
    public LinkedList<Operand> args;
    public Func nfunc;

    public void update() {
        Func caller = super.BelongBB.BelongFunc;
        caller.callee.add(nfunc);
        if (nfunc.name.equals("print") || nfunc.name.equals("println"))
            super.BelongBB.BelongFunc.hasOutput = true;
    }

    public Call(BB bb, Address tpos, Func func, LinkedList<Operand> args) {
        super(bb);
        this.tpos = tpos;
        this.nfunc = func;
        this.args = new LinkedList<>(args);
        //update();
    }

    public Call(BB bb, Address tpos, Func func, Operand... args) {
        super(bb);
        this.tpos = tpos;
        this.nfunc = func;
        this.args = new LinkedList<>(Arrays.asList(args));
        //update();
    }

    @Override
    public Inst copy(BB bb) {
        return new Call(bb, tpos, nfunc, args);
    }

    boolean isVirReg(Operand pos){
        return pos instanceof VirtualReg;
    }
    boolean isReg(Operand pos){
        return pos instanceof Register;
    }
    boolean isAllo(Operand pos){
        return pos instanceof AlloSpace;
    }

    public LinkedList<Register> getCallUsed() {
        LinkedList<Register> registers = new LinkedList<>();
        for (Operand operand: args) {
            if (isVirReg(operand)){
                registers.add((Register)operand);
            } else {
                if (isAllo(operand)) {
                    registers.addAll(((AlloSpace) operand).getUseRegs());
                }
                else {
                    continue;
                }
            }
        }
        return registers;
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {}

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (isReg(tpos)) {
            if(renameMap.containsKey((Register)tpos)) {
                tpos = renameMap.get((Register)tpos);
            }
        }
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        return new LinkedList<>(Regs.vcallerSave);
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        return new LinkedList<>(Regs.vargs.subList(0, min(6, args.size())));
    }

    @Override
    public LinkedList<Stack> getStackSlots() {
        LinkedList<Stack> slots = new LinkedList<>(defualtGetSlot(tpos));
        for (Operand op: args) {
            if (op instanceof Stack) {
                slots.add((Stack)op);
            }
        }
        return slots;
    }


    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}