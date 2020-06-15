package Mxstar.IR.Operand;

import Mxstar.IR.IRVisitor;
public class PhysicalReg extends Register{
    public String name;

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}