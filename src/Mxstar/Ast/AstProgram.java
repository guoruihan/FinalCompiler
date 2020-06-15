package Mxstar.Ast;
import java.util.LinkedList;
import java.util.List;

public class AstProgram extends AstNode{
    public List<FuncDecl> funcDecl;
    public List<ClassDecl> classDecl;
    public List<VarDecl> globalVariables;
    public List<Decl> decl;

    public AstProgram() {
        funcDecl = new LinkedList<>();
        classDecl = new LinkedList<>();
        globalVariables = new LinkedList<>();
        decl = new LinkedList<>();
    }

    public void add(FuncDecl funcDeclaration) {
        this.funcDecl.add(funcDeclaration);
        decl.add(funcDeclaration);
    }

    public void add(ClassDecl classDeclaration) {
        this.classDecl.add(classDeclaration);
        decl.add(classDeclaration);
    }

    public void addAll(List<VarDecl> variableDeclaration) {
        this.globalVariables.addAll(variableDeclaration);
        decl.addAll(variableDeclaration);
    }

    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}