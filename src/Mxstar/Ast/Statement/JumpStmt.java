package Mxstar.Ast.Statement;
import Mxstar.Ast.Expr.*;

import Mxstar.Ast.*;
public class JumpStmt extends Stmt{
    public Expr retExpr = null; // if it is a return jump then this will have true value
    public boolean isReturn;
    public boolean isBreak;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}