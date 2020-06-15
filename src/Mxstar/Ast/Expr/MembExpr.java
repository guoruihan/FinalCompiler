package Mxstar.Ast.Expr;

import Mxstar.Ast.*;
public class MembExpr extends Expr{
    //what's this?
    public Expr object;
    public Identifier fieldAccess;
    public FuncCallExpr methodCall;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}