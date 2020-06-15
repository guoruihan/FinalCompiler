package Mxstar.Ast;

import Mxstar.FrontEnd.*;

public class ClassTypeNode extends Type{
    public String name;

    public ClassTypeNode() {
        name = null;
    }
    public ClassTypeNode(String name) {
        this.name = name;
    }
    public ClassTypeNode(String name, Position nposition) {
        this.name = name;
        this.position = nposition;
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}
