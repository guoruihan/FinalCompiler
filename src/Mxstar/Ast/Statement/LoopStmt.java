package Mxstar.Ast.Statement;

import Mxstar.Ast.Expr.Expr;

import Mxstar.Ast.*;
public class LoopStmt extends Stmt {
    public Stmt startStmt;
    public Expr condition;
    public Stmt updateStmt;
    public Stmt body;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}