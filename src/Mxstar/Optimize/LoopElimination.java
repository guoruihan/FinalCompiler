package Mxstar.Optimize;

import Mxstar.Ast.*;
import Mxstar.Ast.Expr.*;
import Mxstar.Ast.Statement.*;
import Mxstar.Symbol.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LoopElimination implements AstVisitor {
    //idea referenced by LZY@apolloLiZhaoYu
    private HashMap<LoopStmt, HashSet<VarSymbol>> loopUseVariables;
    private HashSet<LoopStmt> UsefulLoops;
    private LinkedList<LoopStmt> curLoop;
    private HashSet<VarSymbol> allUsedVariables;
    public AstProgram astProgram;

    public LoopElimination(AstProgram astProgram) {
        this.astProgram = astProgram;
        curLoop = new LinkedList<>();
        UsefulLoops = new HashSet<>();
        loopUseVariables = new HashMap<>();
        allUsedVariables = new HashSet<>();
    }


    @Override
    public void visit(AstProgram node) {
        for (FuncDecl funcDeclaration: node.funcDecl) {
            funcDeclaration.accept(this);
        }
        for (ClassDecl classDeclaration: node.classDecl) {
            classDeclaration.accept(this);
        }
    }

    @Override
    public void visit(Decl node) {

    }

    @Override
    public void visit(ClassDecl node) {
        if (node.construct != null) {
            node.construct.accept(this);
        }
        for (FuncDecl d: node.methods) {
            d.accept(this);
        }
    }

    @Override
    public void visit(FuncDecl node) {
        loopUseVariables.clear();
        curLoop.clear();
        UsefulLoops.clear();
        for (VarDecl d: node.parameters) {
            d.accept(this);
        }
        for (Stmt st: node.body){
            st.accept(this);
        }
//        for (int i = node.body.size() - 1; i >= 0; --i) {
//            node.body.get(i).accept(this);
//        }


        List<LoopStmt> deleteList = new LinkedList<>();
        int cnt = 0;
        for (Stmt st: node.body) {
            if (st instanceof LoopStmt) {
                cnt++;
                if(!UsefulLoops.contains(st)) {
                    deleteList.add((LoopStmt) st);
//                     System.out.println("delete loop!" + cnt);
                }
            }
        }
        node.body.removeAll(deleteList);
    }

    @Override
    public void visit(VarDecl node) {
        if (!node.symbol.isGlobalVariable && !node.symbol.isClassField) {
            if (!curLoop.isEmpty())
                loopUseVariables.get(curLoop.getLast()).add(node.symbol);
        } else  {
//            System.out.println("var decl");
            UsefulLoops.addAll(curLoop);
        }
        if (node.init != null)
            node.init.accept(this);
    }

    @Override
    public void visit(Type node) {

    }

    @Override
    public void visit(ArrayType node) {
    }

    @Override
    public void visit(PrimitiveTypeNode node) {

    }

    @Override
    public void visit(ClassTypeNode node) {

    }

    @Override
    public void visit(Stmt node) {

    }

    @Override
    public void visit(LoopStmt node) {
        loopUseVariables.put(node, new HashSet<>());
        curLoop.addLast(node);
        if (node.condition != null) {
            node.condition.accept(this);
        }
        if (node.updateStmt != null) {
            node.updateStmt.accept(this);
        }
        if (node.startStmt != null) {
            node.startStmt.accept(this);
        }
        if (node.body != null) {
            node.body.accept(this);
        }
        curLoop.removeLast();
        if (!curLoop.isEmpty())
            loopUseVariables.get(curLoop.getLast()).addAll(loopUseVariables.get(node));
        allUsedVariables.addAll(loopUseVariables.get(node));
    }

    @Override
    public void visit(JumpStmt node) {
        if (node.isReturn) {
//            System.out.println("jump");
            UsefulLoops.addAll(curLoop);
            if (node.retExpr != null) {
                node.retExpr.accept(this);
            }
        }
    }

    @Override
    public void visit(CondStmt node) {
        node.expression.accept(this);
        if(node.thenStmt != null) {
            node.thenStmt.accept(this);
        }
        if (node.elseStmt != null) {
            node.elseStmt.accept(this);
        }
    }

    @Override
    public void visit(BlockStmt node) {
//        for (int i = node.statements.size() - 1; i >= 0; --i) {
//            node.statements.get(i).accept(this);
//        }
        for (Stmt st: node.statements)
            st.accept(this);
    }

    @Override
    public void visit(VarDeclStmt node) {
        node.declaration.accept(this);
    }

    @Override
    public void visit(ExprStmt node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(Expr node) {

    }

    @Override
    public void visit(Identifier node) {
        if (node.name.equals("this")) {
//            System.out.println("this");
            UsefulLoops.addAll(curLoop);
        }
        /*if (allUsedVariables.contains(node.symbol))
            UsefulLoops.addAll(curLoop);
        else {
            if (!curLoop.isEmpty())
                loopUseVariables.get(curLoop.getLast()).add(node.symbol);
            else
               allUsedVariables.add(node.symbol);
        }*/
        if (!node.symbol.isClassField && !node.symbol.isGlobalVariable) {
            for (LoopStmt loopStmt: loopUseVariables.keySet()) {
                if (curLoop.contains(loopStmt)) {
                    loopUseVariables.get(loopStmt).add(node.symbol);
                } else {
//                    System.out.println("loop: " + node.symbol.name);
                    if (loopUseVariables.get(loopStmt).contains(node.symbol)){
                        UsefulLoops.add(loopStmt);
                    }
                }
            }
        } else {
//            System.out.println("global" + node.symbol.name);
            UsefulLoops.addAll(curLoop);
        }
    }

    @Override
    public void visit(LiteralExpr node) {

    }

    @Override
    public void visit(ArrayExpr node) {
        node.expr.accept(this);
        node.idx.accept(this);
    }

    @Override
    public void visit(FuncCallExpr node) {
        if (node.functionSymbol == null) {
//            System.out.println("no symbol");
        }
        UsefulLoops.addAll(curLoop);
        for (Expr expr: node.arguments)
            expr.accept(this);
    }

    @Override
    public void visit(NewExpr node) {
//        System.out.println("no new expr");
        UsefulLoops.addAll(curLoop);
        for (Expr expr: node.exprDim)
            expr.accept(this);

    }

    @Override
    public void visit(MembExpr node) {
        node.object.accept(this);
        if (node.object.type instanceof ArrayType)
            return ;
        if (node.fieldAccess != null)
            node.fieldAccess.accept(this);
        else
            node.methodCall.accept(this);
    }

    @Override
    public void visit(UnaryExpr node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(BinaryExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
    }

    @Override
    public void visit(EmptyStmt node) {

    }
}
