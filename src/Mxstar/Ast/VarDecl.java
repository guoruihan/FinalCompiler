
package Mxstar.Ast;

import Mxstar.Ast.Expr.*;
import Mxstar.Symbol.*;

public class VarDecl extends  Decl {
    public Type type;
    public String name;
    public Expr init;

    public VarSymbol symbol = null;

    public VarDecl() {
        type = null;
        name = null;
        init = null;
    }
    public VarDecl(Type type, String name) {
        this.type = type;
        this.name = name;
    }
    public VarDecl(Type type, String name, Expr init) {
        this.type = type;
        this.name = name;
        this.init = init;
    }

    public VarDecl(Type type, String name, Expr init, VarSymbol symbol) {
        this.type = type;
        this.name = name;
        this.init = init;
        this.symbol = symbol;
    }//Still don't know what's this

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}