package Mxstar.IR.Operand;

import Mxstar.IR.Func;
import Mxstar.IR.IRVisitor;

public class FuncAddr extends Constant {
    public Func func;

    public FuncAddr(Func func) {
        this.func = func;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
