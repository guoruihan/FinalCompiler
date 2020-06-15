package Mxstar.Ast.Expr;
import Mxstar.Ast.*;
public class AssignExpr extends Expr {
    public Expr lhs, rhs;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }

}