
package Mxstar.Ast;

import Mxstar.Ast.Expr.*;
import Mxstar.Ast.Statement.*;
import Mxstar.Symbol.ArrayType;

public interface AstVisitor {
    void visit(AstProgram node);

    void visit(Decl node);
    void visit(ClassDecl node);
    void visit(FuncDecl node);
    void visit(VarDecl node);

    void visit(Type node);
    void visit(ArrayType node);
    void visit(PrimitiveTypeNode node);
    void visit(ClassTypeNode node);

    void visit(Stmt node);
    void visit(LoopStmt node);
    void visit(JumpStmt node);
    void visit(CondStmt node);
    void visit(BlockStmt node);
    void visit(VarDeclStmt node);
    void visit(ExprStmt node);

    void visit(Expr node);
    void visit(Identifier node);
    void visit(LiteralExpr node);
    void visit(ArrayExpr node);
    void visit(FuncCallExpr node);
    void visit(NewExpr node);
    void visit(MembExpr node);
    void visit(UnaryExpr node);
    void visit(BinaryExpr node);
    void visit(AssignExpr node);

    void visit(EmptyStmt node);
}