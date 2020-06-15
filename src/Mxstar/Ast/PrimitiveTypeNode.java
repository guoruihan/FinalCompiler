package Mxstar.Ast;

import Mxstar.FrontEnd.*;

public class PrimitiveTypeNode extends Type{
    public String name;

    public PrimitiveTypeNode () {
        name = null;
    }
    public PrimitiveTypeNode (String nname, Position nposition) {
        name = nname;
        position = nposition;
    }
    public PrimitiveTypeNode (String name) {
        this.name = name;
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}