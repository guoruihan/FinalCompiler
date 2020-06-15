package Mxstar.Ast.Expr;

import Mxstar.Symbol.*;

import java.util.List;

import Mxstar.Ast.*;
public class FuncCallExpr extends Expr {
    public String functionName;
    public List<Expr> arguments;

    public FuncSymbol functionSymbol;

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}