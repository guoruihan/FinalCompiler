package Mxstar.IR.Operand;

import Mxstar.IR.IRVisitor;

public class Imm extends Constant {
    public int value;

    public Imm(int value) {
        this.value = value;
    }

    public Imm(Imm other){
        this.value = other.value;
    }

    @Override
    public void accept(IRVisitor visitor){visitor.visit(this);}
}