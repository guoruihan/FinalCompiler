package Mxstar.Ast.Expr;

import org.antlr.v4.runtime.Token;
import Mxstar.FrontEnd.*;
import Mxstar.Symbol.*;

import Mxstar.Ast.*;
public class Identifier extends Expr {
    public String name;

    public VarSymbol symbol;

    public Identifier(){}
    public Identifier(Token token) {
        this.name = token.getText();
        this.position = new Position(token);
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}