package Mxstar.Ast.Expr;

import java.util.List;

import Mxstar.Ast.*;
public class NewExpr extends Expr{
    //what's this
    public Type typeNode;
    public List<Expr> exprDim;
    public int emptyDim;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}