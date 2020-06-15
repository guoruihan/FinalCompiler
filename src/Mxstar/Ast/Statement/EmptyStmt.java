package Mxstar.Ast.Statement;

import Mxstar.Ast.*;
public class EmptyStmt extends Stmt {
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}