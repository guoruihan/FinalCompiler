package Mxstar.Ast;

import Mxstar.Symbol.ClassSymbol;

import java.util.LinkedList;
import java.util.List;

public class ClassDecl extends  Decl {
    public String name;

    public List<VarDecl> fields;
    public List<FuncDecl> methods;
    public FuncDecl construct = null;

    public ClassSymbol symbol;
    public ClassDecl(){
    }
    public ClassDecl(String nname){
        name = nname;
        fields = new LinkedList<>();
        methods = new LinkedList<>();
    }
    @Override
    public String toString() {
        return name + '\n';
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}