package Mxstar.Ast.Expr;

import Mxstar.Ast.*;
public class UnaryExpr extends Expr{
    public String op;
    public Expr expr;


    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}