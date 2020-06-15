package Mxstar.IR.Operand;

import Mxstar.IR.Func;
import Mxstar.IR.IRVisitor;

public class Stack extends AlloSpace {
    public Func func;
    public String hint;

    public Stack(String hint) {
        this.hint = hint;
    }
    public Stack() {
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
