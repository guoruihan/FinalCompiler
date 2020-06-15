package Mxstar.Ast;

import Mxstar.Ast.Statement.*;
import Mxstar.Symbol.*;

import java.util.LinkedList;
import java.util.List;

public class FuncDecl extends  Decl{
    public Type returnType;
    public String name;
    public List<VarDecl> parameters;
    public List<Stmt> body;

    public FuncSymbol symbol;//What's this
    public FuncDecl(){
    }
    public FuncDecl(Type nreturnType,String nname){
        returnType = nreturnType;
        name = nname;
    }

    public static FuncDecl defaultConstructor(String name) {
        FuncDecl funcDecl = new FuncDecl();
        funcDecl.name = name;
        funcDecl.parameters = new LinkedList<>();
        funcDecl.body = new LinkedList<>();
        funcDecl.returnType = new PrimitiveTypeNode("void");
        return funcDecl;
    }

    @Override
    public String toString() {
        return name + '\n';
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}
