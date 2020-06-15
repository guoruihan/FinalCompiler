package Mxstar.IR.Operand;

import Mxstar.IR.IRVisitor;

public abstract class Operand {
    public abstract void accept(IRVisitor visitor);
}
