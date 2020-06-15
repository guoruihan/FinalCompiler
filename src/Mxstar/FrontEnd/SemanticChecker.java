package Mxstar.FrontEnd;

import Mxstar.Ast.*;
import Mxstar.ErrorProcessor.*;
import Mxstar.Symbol.*;
import Mxstar.Ast.Expr.*;
import Mxstar.Ast.Statement.*;

public class SemanticChecker implements AstVisitor {
    private GlobalSymbolTable globalSymbolTable;
    private int loop;
    private FuncSymbol curFunction;
    private CompileErrorListener errorListener;

    public SemanticChecker(CompileErrorListener errorListener, GlobalSymbolTable globalSymbolTable) {
        this.errorListener = errorListener;
        this.loop = 0;
        this.curFunction = null;
        this.globalSymbolTable = globalSymbolTable;
    }

    /*useless ones*/
    @Override
    public void visit(Decl node) {
        assert false;
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
    public void visit(Expr node) {

    }

    @Override
    public void visit(EmptyStmt node) {

    }

    /*useless ones finished*/
    private boolean checkBooleanExpr(VarType type) {
        return !(type instanceof PrimitiveType && ((PrimitiveType) type).name.equals("bool"));
    }

    @Override
    public void visit(LoopStmt node) {
        if (node.startStmt != null)
            node.startStmt.accept(this);

        if (node.condition != null) {
            node.condition.accept(this);
            if (checkBooleanExpr(node.condition.type)) {

                errorListener.addError(node.position, "the condition should be a boolean experssion.");

                return ;
            }
        }
        if (node.updateStmt != null)
            node.updateStmt.accept(this);
        if (node.body != null) {
            loop++;
            node.body.accept(this);
            loop--;
        }
    }

    @Override
    public void visit(JumpStmt node) {
        if (!node.isReturn) {
            if (loop == 0) {

                errorListener.addError(node.position, "here is not in a loop!");

            }
        } else {//return here
//            System.out.println("return!!!");
            VarType requireType = curFunction.returnType;
            PrimitiveType voidType = new PrimitiveType("void", globalSymbolTable.getPrimitiveSymbol("void"));
            if (requireType.match(voidType) && node.retExpr != null) {

                errorListener.addError(node.position, "we cannot return a value");

                return ;
            }
            VarType retType;
            if (node.retExpr == null)
                retType = new PrimitiveType("void", globalSymbolTable.getPrimitiveSymbol("void"));
            else
                retType = node.retExpr.type;
//            System.out.println(((PrimitiveType)requireType).name);
//            System.out.println(retType instanceof ClassType);
            if (!requireType.match(retType)) {

                errorListener.addError(node.position, "the return type does not match");

            }
        }
    }

    @Override
    public void visit(CondStmt node) {
        node.expression.accept(this);
        if (checkBooleanExpr(node.expression.type)) {

            errorListener.addError(node.position, "the condition expression should be boolean");

            return ;
        }
        if(node.thenStmt != null) {
            node.thenStmt.accept(this);
        }
        if (errorListener.hasError()) {
            return ;
        }
        if (node.elseStmt != null) {
            node.elseStmt.accept(this);
        }
    }

    @Override
    public void visit(BlockStmt node) {
        for (Stmt d : node.statements)
            d.accept(this);
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
    public void visit(Identifier node) {
        //if (node.name.equals("this"))
        node.modifiable = (!node.name.equals("this"));
        //else
        //    node.modifiable = true;
    }

    @Override
    public void visit(LiteralExpr node) {
        node.modifiable = false;
    }

    @Override
    public void visit(ArrayExpr node) {

        //System.out.println("mew0");
        node.expr.accept(this);
        node.idx.accept(this);
        if (!(node.idx.type instanceof PrimitiveType) ){
            errorListener.addError(node.position, "there is not enough parameters");

        }
        node.modifiable = true;
    }

    @Override
    public void visit(FuncCallExpr node) {
        int inClass = (node.functionSymbol.parameterNames.size() > 0 && node.functionSymbol.parameterNames.get(0).equals("this")) ? 1 : 0;
        if (node.arguments.size() + inClass != node.functionSymbol.parameterTypes.size()) {
//            System.out.println(node.arguments.size());
//            System.out.println(inClass);
//            System.out.println(node.functionSymbol.parameterTypes.size());

            errorListener.addError(node.position, "there is not enough parameters");

        } else {
            for (int i = 0; i < node.arguments.size(); ++i) {
                node.arguments.get(i).accept(this);

                if (!node.arguments.get(i).type.match(node.functionSymbol.parameterTypes.get(i + inClass))) {

                    errorListener.addError(node.position, "the parameter type does not match");

                    break;
                }
            }
        }
        node.modifiable = false;
    }

    @Override
    public void visit(NewExpr node) {
        for (Expr e : node.exprDim)
            e.accept(this);
        node.modifiable = false;
    }

    @Override
    public void visit(MembExpr node) {
        node.object.accept(this);
        if (node.object.type instanceof ArrayType) {
            node.modifiable = false;
        } else {
            if (node.methodCall != null) {
                node.methodCall.accept(this);
                node.modifiable = false;
            } else {
                node.modifiable = true;
            }
        }
    }

    private boolean isIntType(VarType type) {
        return type instanceof PrimitiveType && ((PrimitiveType)type).name.equals("int");
    }
    private boolean isBoolType(VarType type) {
        return type instanceof PrimitiveType && ((PrimitiveType)type).name.equals("bool");
    }
    private boolean isStringType(VarType type) {
        return type instanceof ClassType && ((ClassType)type).name.equals("string");
    }

    @Override
    public void visit(UnaryExpr node) {
        node.expr.accept(this);
        boolean modifiedError = false;
        boolean typeError = false;
        switch (node.op) {
            case "v++": case "v--":
                if (!node.expr.modifiable)
                    modifiedError = true;
                if (!isIntType(node.type))
                    typeError = true;
                node.modifiable = false;
                break;
            case "++v": case "--v":
                if (!node.expr.modifiable)
                    modifiedError = true;
                if (!isIntType(node.type))
                    typeError = true;
                node.modifiable = true;
                break;
            case "!":
                if (!isBoolType(node.type))
                    typeError = true;
                node.modifiable = false;
                break;
            case "~":
                if (!isIntType(node.type))
                    typeError = true;
                node.modifiable = false;
                break;
            default:
                assert false;
        }
        if (typeError) {

            errorListener.addError(node.position, "type error occured");
        } else if (modifiedError) {
            errorListener.addError(node.position, "Error! This can not be modified");

        }
    }

    public void BinaryExpr_pre(BinaryExpr node){

        node.lhs.accept(this);
        node.rhs.accept(this);
    }
    public boolean switchOp(BinaryExpr node){
        boolean tag = false;
        switch (node.op) {
            case "<<": case ">>": case "&": case "|": case "^":case "-": case "*": case "/": case "%":
                if (!isIntType(node.lhs.type))
                    tag = true;
                break;
            case "<=": case ">=": case "<" : case ">": case "+":
                if (!isIntType(node.lhs.type) && !isStringType(node.lhs.type))
                    tag = true;
                break;
            case "&&": case "||":
                if (!isBoolType(node.lhs.type))
                    tag = true;
                break;
            default:
                break;
        }
        return tag;
    }
    @Override
    public void visit(BinaryExpr node) {
        BinaryExpr_pre(node);
        if (!node.lhs.type.match(node.rhs.type)) {
//            System.out.println(node.rhs.type instanceof  ClassType );

            errorListener.addError(node.position, "type can not match");
        } else {
            boolean typeError = switchOp(node);
            if (typeError) {
                errorListener.addError(node.position, "type error occured");

            }
        }
        node.modifiable = false;
    }

    public void assignExpr_pre(AssignExpr node){
        node.rhs.accept(this);
        node.lhs.accept(this);
    }

    @Override
    public void visit(AssignExpr node) {
        assignExpr_pre(node);
        //System.out.println("mew123");
        if (!node.lhs.type.match(node.rhs.type)) {

            errorListener.addError(node.position, "assignment different type");
        }
        if (!node.lhs.modifiable) {
            errorListener.addError(node.position, "left val not able to modify");

        }
        node.modifiable = false;
    }



    @Override
    public void visit(ClassDecl node) {
        for (FuncDecl d : node.methods) {
            d.accept(this);
        }
        if (node.construct != null)
            node.construct.accept(this);
    }

    @Override
    public void visit(FuncDecl node) {
        curFunction = node.symbol;
        for (Stmt d : node.body) {
            d.accept(this);
        }
    }

    @Override
    public void visit(VarDecl node) {
        if (node.init != null && !node.symbol.variableType.match(node.init.type)) {

            errorListener.addError(node.position, "they should be in the same type!");

        }
    }

    @Override
    public void visit(AstProgram node) {
        for (FuncDecl decl: node.funcDecl){
            decl.accept(this);
        }
        if (errorListener.hasError())
            return ;
        for (ClassDecl d : node.classDecl) {
            d.accept(this);
        }
        if (errorListener.hasError())
            return ;
        for (VarDecl d : node.globalVariables) {
            d.accept(this);
        }

        FuncSymbol functionSymbol = globalSymbolTable.getFS("main");
        if (functionSymbol == null) {
            errorListener.addError(node.position, "there are some problem with the main function.");

            return ;
        }
//        FuncSymbol symbol = functionSymbol;
        if (!(functionSymbol.returnType instanceof PrimitiveType && ((PrimitiveType)functionSymbol.returnType).name.equals("int"))) {

            errorListener.addError(node.position, "the main should has return value with integer.");
            return ;
        }
        if (functionSymbol.parameterNames.size() != 0) {
            errorListener.addError(node.position, "the main should not have any parameters.");


        }
    }
}
