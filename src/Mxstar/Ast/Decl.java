package Mxstar.Ast;

public class Decl extends AstNode{
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}