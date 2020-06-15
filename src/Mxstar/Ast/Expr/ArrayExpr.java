package Mxstar.Ast.Expr;
import Mxstar.Ast.*;
public class ArrayExpr extends Expr{
    public Expr expr;
    public Expr idx;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}