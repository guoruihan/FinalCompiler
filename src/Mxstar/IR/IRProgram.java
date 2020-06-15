package Mxstar.IR;

import Mxstar.IR.Operand.ConstVal;

import java.util.LinkedList;

public class IRProgram {
    public LinkedList<Func> funcs;
    public LinkedList<ConstVal> constVal;

    public IRProgram() {
        funcs = new LinkedList<>();
        constVal = new LinkedList<Mxstar.IR.Operand.ConstVal>();
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
