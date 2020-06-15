package Mxstar.Ast.Statement;

import java.util.List;

import Mxstar.Ast.*;
public class BlockStmt extends Stmt{

    public List<Stmt> statements;

    public BlockStmt() {
        statements = null;
    }
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}