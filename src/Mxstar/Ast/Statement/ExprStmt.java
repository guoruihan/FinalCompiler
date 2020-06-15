package Mxstar.Ast.Statement;
import Mxstar.Ast.Expr.*;

import Mxstar.Ast.*;
import Mxstar.FrontEnd.*;

public class ExprStmt extends Stmt {
    public Expr expression;

    public ExprStmt() {
        expression = null;
    }
    public ExprStmt (Expr expression){
        this.expression = expression;
    }
    public  ExprStmt(Position nposition, Expr nexpr){
        position = nposition;
        expression = nexpr;
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}