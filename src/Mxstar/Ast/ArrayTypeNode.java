package Mxstar.Ast;

import Mxstar.FrontEnd.*;

public class ArrayTypeNode extends Type {
    public Type arraytype = null;
    public int dim;
    public ArrayTypeNode () {
    }
    public ArrayTypeNode (Position nlocation,int ndim) {
        position = nlocation;
        dim = ndim;
    }
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}