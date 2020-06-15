package Mxstar.IR.Operand;

import Mxstar.IR.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class AlloSpace extends Address {
    public Register base = null;
    public Register index = null;
    public int scale = 0;
    public String name;
    public Constant constant = null;

    public AlloSpace(){ }
    public AlloSpace(VirtualReg base) {
        this.base = base;
    }
    public  AlloSpace(VirtualReg base, VirtualReg index, int scale) {
        this.base = base;
        this.index = index;
        this.scale = scale;
    }

    public AlloSpace(VirtualReg index, int scale, Constant constant) {
        this.index = index;
        this.scale = scale;
        this.constant = constant;
    }

    public AlloSpace(Constant constant, String name) {
        this.constant = constant;
        this.name = name;
//        System.out.println(this);
    }
    public AlloSpace(Register base, Constant constant) {
        this.base = base;
        this.constant = constant;
    }
    public AlloSpace(Register base, Register index, int scale, Constant constant) {
        this.base = base;
        this.index = index;
        this.scale = scale;
        this.constant = constant;
    }
    public AlloSpace(Register base, Register index, int scale, Constant constant,String name) {
        this.base = base;
        this.index = index;
        this.scale = scale;
        this.constant = constant;
        this.name = name;
    }



    public AlloSpace copy() {
        if (this instanceof Stack) {
            return this;
        }
        return new AlloSpace(base, index, scale, constant,name);
    }

    public LinkedList<Register> getUseRegs(){
        LinkedList<Register> regs = new LinkedList<>();
        if (base != null) {
            regs.add(base);
        }
        if (index != null) {
            regs.add(index);
        }
        return regs;
    }

    public void renameUseRegs(HashMap<Register, Register> renameMap) {
        if (renameMap.containsKey(base)) {
            base = renameMap.get(base);
        }
        if (renameMap.containsKey(index)) {
            index = renameMap.get(index);
        }
    }



    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}