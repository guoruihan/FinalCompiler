package Mxstar.Ast.Expr;
import Mxstar.Ast.*;
public class BinaryExpr extends Expr {
    public Expr lhs, rhs;
    public String op;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}