package Mxstar.IR.Inst;
import Mxstar.IR.*;
import Mxstar.IR.Operand.Operand;

import Mxstar.IR.Operand.*;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Inst {
    public BB BelongBB;
    public Inst prev = null,next = null;
    public abstract Inst copy(BB BelongBB);
    public void prepend(Inst inst){
        if(prev == null){
            BelongBB.head = inst;
        } else {
            prev.next = inst;
            inst.prev = prev;
        }
        inst.next = this;
        prev = inst;
    }
    public void append(Inst inst){
        if(next == null){
            BelongBB.tail = inst;
        } else {
            this.next.prev = inst;
            inst.next = next;
        }
        inst.prev = this;
        this.next = inst;
    }

    public void remove() {
        if (prev == null && next == null) {
            BelongBB.head = BelongBB.tail = null;
        } else if (prev == null){
            BelongBB.head = next;
            next.prev = null;
        } else if (next == null) {
            BelongBB.tail = prev;
            prev.next = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
    }

    public void replace(Inst inst) {
        if (prev == null && next == null) {
            BelongBB.head = BelongBB.tail = inst;
        } else if (prev == null) {
            BelongBB.head = inst;
            next.prev = inst;
            inst.next = next;
            inst.prev = null;
        } else if (next == null) {
            BelongBB.tail = inst;
            prev.next = inst;
            inst.prev = prev;
            inst.next = null;
        } else {
            next.prev = inst;
            prev.next = inst;
            inst.next = next;
            inst.prev = prev;
        }
    }
    
    public Inst(){
        this.BelongBB = null;
    }

    public Inst(BB BelongBB) {
        this.BelongBB = BelongBB;
    }


    public abstract LinkedList<Register> getDefRegs();
    public abstract LinkedList<Register> getUseRegs();
    public abstract void renameUseReg(HashMap<Register, Register> renameMap);
    public abstract void renameDefReg(HashMap<Register, Register> renameMap);
    public abstract LinkedList<Stack> getStackSlots();


    LinkedList<Stack> defualtGetSlot(Operand... operands) {
        LinkedList<Stack> slots = new LinkedList<>();
        for (Operand op: operands) {
            if (op instanceof Stack) {
                slots.add((Stack) op);
            }
        }
        return slots;
    }

    public abstract void accept(IRVisitor visitor);
}
