package Mxstar.Ast.Statement;

import Mxstar.Ast.VarDecl;

import Mxstar.Ast.*;
public class VarDeclStmt extends Stmt {
    public VarDecl declaration;

    /*1*/ @Override public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}