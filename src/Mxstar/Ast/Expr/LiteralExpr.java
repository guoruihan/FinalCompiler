package Mxstar.Ast.Expr;

import org.antlr.v4.runtime.Token;
import Mxstar.FrontEnd.*;

import static Parser.MxstarLexer.*;

import Mxstar.Ast.*;
public class LiteralExpr extends Expr {
    public String typeName;
    public String value;

    public LiteralExpr(Token token) {
        position = new Position(token);
        switch (token.getType()) {
            case Integer_Literal:
                typeName = "int";
                break;
            case NULL_Literal:
                typeName = "null";
                break;
            case Bool_Literal:
                typeName = "bool";
                break;
            default:
                typeName = "string";
        }
        if(token.getType() == Integer_Literal || token.getType() == NULL_Literal || token.getType() == Bool_Literal){
            value = token.getText();
        } else {
            value = escape(token.getText());
        }
    }
    
    private String escape(String string) {
        StringBuilder nstr = new StringBuilder();
        int len = string.length();
        for (int i = 0; i < len; ++i) {
            char c = string.charAt(i);
            if (c == '\\') {
                char nc = string.charAt(i + 1);
                ++i;
                if (nc == 'n') {
                    nstr.append('\n');
                } else {
                    if (nc == 't') {
                        nstr.append('\t');
                    } else {
                        if (nc == '\\') {
                            nstr.append('\\');
                        } else {
                            if (nc == '"') {
                                nstr.append('"');
                            } else {
                                nstr.append(nc);
                            }
                        }
                    }
                }
            } else {
                nstr.append(c);
            }
        }
        return nstr.toString();
    }

    @Override public void accept(AstVisitor visitor) { visitor.visit(this); }

}