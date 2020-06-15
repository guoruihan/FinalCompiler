package Mxstar.FrontEnd;

import Mxstar.Ast.AstNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;



public class Position {
    private final int p1, p2;

    public Position(Token token) {
        this.p1 = token.getLine();
        this.p2 = token.getCharPositionInLine();
    }

    public Position(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }


    public Position(ParserRuleContext ctx) {
        this(ctx.start);
    }

    public Position(AstNode node) {
        this.p1 = node.position.p1;
        this.p2 = node.position.p2;
    }

    @Override
    public String toString() {
        return "(" + p1 + "," + p2 + ")";
    }
}