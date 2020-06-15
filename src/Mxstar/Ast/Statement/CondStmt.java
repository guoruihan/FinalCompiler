package Mxstar.Ast.Statement;
import Mxstar.Ast.Expr.*;

import Mxstar.Ast.*;
public class CondStmt extends Stmt {
    public Expr expression;
    public Stmt thenStmt;
    public Stmt elseStmt;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}