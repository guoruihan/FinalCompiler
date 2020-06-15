package Mxstar.FrontEnd;

import Mxstar.ErrorProcessor.*;
import Mxstar.Ast.*;
import Mxstar.Symbol.*;
import Mxstar.Ast.Statement.*;
import Mxstar.Ast.Expr.*;

import java.util.HashMap;

public class SymbolTableBuilder implements AstVisitor {

    public SymbolTable nSymT;
    public GlobalSymbolTable gSymT;
    public CompileErrorListener errorListener;
/*
    public SymbolTable curSymbolTable;
    public GlobalSymbolTable globalSymbolTable;
    public CompileErrorListener errorListener;
    public FuncSymbol curFunc;
*/
    public HashMap<SymbolTable, ClassSymbol> classHash;


    public SymbolTableBuilder(CompileErrorListener errorListener) {
        this.errorListener = errorListener;

        nSymT = gSymT = new GlobalSymbolTable();
        classHash = new HashMap<>();
    }

    @Override
    public void visit(Stmt node) {
        //System.out.println("visited Stmt!");
        assert false;
    }

    @Override
    public void visit(Type node) {
        //System.out.println("visited Type!");
    }

    @Override
    public void visit(ArrayType node) {
        //System.out.println("visited ArrayType!");
    }

    @Override
    public void visit(PrimitiveTypeNode node) {
        //System.out.println("visited PrimitiveTypeNode!");
    }

    @Override
    public void visit(ClassTypeNode node) {
        //System.out.println("visited ClassTypeNode!");
    }

    @Override
    public void visit(ClassDecl node) {
        //System.out.println("visited ClassDecl!");
    }

    @Override
    public void visit(FuncDecl node) {
        //System.out.println("visited FuncDecl!");
    }

    @Override
    public void visit(VarDecl node) {
        //System.out.println("visited VarDecl!");
        defineVariable(node);
    }

    @Override
    public void visit(Decl node) {
        //System.out.println("visited Decl!");
        assert  false;
    }

    public void GoOnStmt(Stmt nstmt){
        if(nstmt != null)
            nstmt.accept(this);
    }

    public void GoOnExpr(Expr nexpr){
        if(nexpr != null)
            nexpr.accept(this);
/*
        globalSymbolTable = new GlobalSymbolTable();
        curSymbolTable = globalSymbolTable;
        classHash = new HashMap<>();
    }















    @Override
    public void visit(Decl node) {
        assert  false;
    }

    @Override
    public void visit(ClassDecl node) {

    }

    @Override
    public void visit(FuncDecl node) {

    }

    @Override
    public void visit(VarDecl node) {
        defineVariable(node);
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
        assert false;
*/
    }

    @Override
    public void visit(LoopStmt node) {

        GoOnStmt(node.startStmt);
        GoOnExpr(node.condition);
        GoOnStmt(node.updateStmt);
        GoOnStmt(node.body);
/*
        if (node.startStmt != null)
            node.startStmt.accept(this);
        if (node.condition != null)
            node.condition.accept(this);
        if (node.updateStmt != null)
            node.updateStmt.accept(this);
        if (node.body != null)
            node.body.accept(this);
*/
    }

    @Override
    public void visit(JumpStmt node) {

        GoOnExpr(node.retExpr);
/*
        if (node.retExpr != null)
            node.retExpr.accept(this);
*/
    }

    @Override
    public void visit(CondStmt node) {

        GoOnExpr(node.expression);
        GoOnStmt(node.elseStmt);
        GoOnStmt(node.thenStmt);
/*
        if (node.expression != null)
            node.expression.accept(this);
        if (node.elseStmt != null)
            node.elseStmt.accept(this);
        if (node.thenStmt != null)
            node.thenStmt.accept(this);
*/
    }

    @Override
    public void visit(BlockStmt node) {

//        System.out.println("visit BlockStmt!");
        SymbolTable symbolTable = new SymbolTable(nSymT);
        symbolTable.parent = nSymT;
        nSymT = symbolTable;
        for (Stmt d: node.statements) {
            d.accept(this);
        }
        nSymT = nSymT.parent;
    }


    @Override
    public void visit(Expr node) {
        //System.out.println("anything fuck can happen");
        //assert false;
    }
    
/*
//        System.out.println("in block stmt now");
        SymbolTable symbolTable = new SymbolTable(curSymbolTable);
        enter(symbolTable);
        for (Stmt d: node.statements) {
            d.accept(this);
        }
        leave();
    }

*/
    @Override
    public void visit(VarDeclStmt node) {
        node.declaration.accept(this);
    }

    @Override
    public void visit(ExprStmt node) {
//        System.out.println("in expr stmt now");
        node.expression.accept(this);
    }


    public FuncSymbol nFunc;
    @Override
    public void visit(Identifier node) {
        VarSymbol symbol = getVS(node.name, nSymT);
        if(symbol != null){
            node.type = symbol.variableType;
            node.symbol = symbol;
            if (symbol.isGlobalVariable) {
                if (nFunc != null) {
                    nFunc.usedGlobalVariables.add(symbol);
                } else {
                    gSymT.globalInitVars.add(symbol);
                }
            }
        } else {
            errorListener.addError(node.position, "cannot resolve variable");
            node.type = null;
/*
    @Override
    public void visit(Expr node) {
        assert false;
    }

    @Override
    public void visit(Identifier node) {
        VarSymbol symbol = resolveVariableSymbol(node.name, curSymbolTable);
        if (symbol == null) {
            errorListener.addError(node.location, "cannot resolve variable");
            node.type = null;
        } else {
            node.type = symbol.variableType;
            node.symbol = symbol;
            if (symbol.isGlobalVariable) {
                if (curFunc == null) {
                    globalSymbolTable.globalInitVars.add(symbol);
                } else {
                    curFunc.usedGlobalVariables.add(symbol);
                }
            }
*/
        }
    }

    @Override
    public void visit(LiteralExpr node) {

//        System.out.print("Hello Lit");
        if(node.typeName.equals("int") || node.typeName.equals("bool")){
            node.type = new PrimitiveType(node.typeName, gSymT.getPrimitiveSymbol(node.typeName));
        } else {
            node.type = new ClassType(node.typeName, gSymT.getClassSymbol(node.typeName));
/*
//        System.out.print("now in literal expression:"+ node.location + node.typeName);
        switch (node.typeName) {
            case "int":
                node.type = new PrimitiveType("int", globalSymbolTable.getPrimitiveSymbol("int"));
                break;
            case "bool":
                node.type = new PrimitiveType("bool", globalSymbolTable.getPrimitiveSymbol("bool"));
                break;
            case "null":
                node.type = new ClassType("null", globalSymbolTable.getClassSymbol("null"));
                break;
            case "string":
//                System.out.print(node.location);
                node.type = new ClassType("string", globalSymbolTable.getClassSymbol("string"));
                break;
            default:
                assert false;
*/
        }
    }

    public void ArrayExpr_pre(ArrayExpr node){
        node.expr.accept(this);
        node.idx.accept(this);
    }
    @Override
    public void visit(ArrayExpr node) {
        ArrayExpr_pre(node);
        if(!(node.idx.type instanceof PrimitiveType)){

            errorListener.addError(node.position, "index must be integer");
        }
        if (!(node.expr.type instanceof ArrayType)){
            node.type = null;
            errorListener.addError(node.position, "not array idot");
        }
        else {
            node.type = ((ArrayType) node.expr.type).baseType;
/*
            errorListener.addError(node.location, "index must be integer");
        }
        if (node.expr.type instanceof ArrayType)
            node.type = ((ArrayType) node.expr.type).baseType;
        else {
            node.type = null;
            errorListener.addError(node.location, "This is not a array type");
*/
        }
    }

    @Override
    public void visit(FuncCallExpr node) {

        FuncSymbol functionSymbol = getFS(node.functionName, nSymT);
        if (functionSymbol == null) {
            errorListener.addError(node.position, "not a function like this here");
        } else {
            for (Expr nexpr : node.arguments)
                nexpr.accept(this);
            node.functionSymbol = functionSymbol;
            node.type = functionSymbol.returnType;
        }
/*
        FuncSymbol functionSymbol = resolveFunctionSymbol(node.functionName, curSymbolTable);
        if (functionSymbol == null) {
            errorListener.addError(node.location, "can not find the function you want here");
            return ;
        }
        for (Expr e : node.arguments)
            e.accept(this);
        node.type = functionSymbol.returnType;
        node.functionSymbol = functionSymbol;
*/
    }

    public void NewExpr_pre(NewExpr node){
        for (Expr e: node.exprDim) {
            e.accept(this);

            //System.out.println(((PrimitiveType)e.type).name);
            if(((PrimitiveType)e.type).name != "int"){
                errorListener.addError(node.position, "index must be integer");
/*
            System.out.println(((PrimitiveType)e.type).name);
            if(((PrimitiveType)e.type).name != "int"){
                errorListener.addError(node.location, "index must be integer");
*/
            }
        }
    }
    @Override
    public void visit(NewExpr node) {
        NewExpr_pre(node);
        int dimension = node.exprDim.size() + node.emptyDim;

        node.type = getVT(node.typeNode);
        //fucking empty qwq
//        System.out.println(node.type);
//        System.out.println(node.position);
//        System.out.println(dimension);
        if (node.type == null) {
            errorListener.addError(node.position, "cannot resolve the type");
/*
        node.type = resolveVariableType(node.typeNode);
        //fucking empty qwq
//        System.out.println(node.type);
//        System.out.println(node.location);
//        System.out.println(dimension);
        if (node.type == null) {
            errorListener.addError(node.location, "cannot resolve the type");
*/
            node.type = null;
            return ;
        }
        if (dimension == 0 && node.typeNode instanceof PrimitiveTypeNode && ((PrimitiveTypeNode) node.typeNode).name.equals("void")) {

            errorListener.addError(node.position, "cannot new void");
/*
            errorListener.addError(node.location, "cannot new void");
*/
            node.type = null;
            return ;
        }
        for (int i = 0; i < dimension; ++i) {
            node.type = new ArrayType(node.type);
        }
    }

    @Override
    public void visit(MembExpr node) {
        node.object.accept(this);
        if (node.object.type instanceof  PrimitiveType) {

            errorListener.addError(node.position, "this is not a class");
        } else if (node.object.type instanceof ArrayType) {
//            ArrayType arrayType = (ArrayType)node.object.type;
            if (node.methodCall == null || !node.methodCall.functionName.equals("size")) {
                errorListener.addError(node.position, "this is not a class");
            } else {
                node.type = new PrimitiveType("int", gSymT.getPrimitiveSymbol("int"));
/*
            errorListener.addError(node.location, "this is not a class");
        } else if (node.object.type instanceof ArrayType) {
//            ArrayType arrayType = (ArrayType)node.object.type;
            if (node.methodCall == null || !node.methodCall.functionName.equals("size")) {
                errorListener.addError(node.location, "this is not a class");
            } else {
                node.type = new PrimitiveType("int", globalSymbolTable.getPrimitiveSymbol("int"));
*/
            }
        } else {
            ClassType classType = (ClassType) node.object.type;
            /*if (classType == null) {
                System.out.println("no such class type");
            }
            SymbolTable symbolTable = classType.Mxstar.Symbol.symbolTable;
            enter(symbolTable);*/
            if (node.methodCall != null) {
//                if (classType.symbol == null) {

//                    System.out.println(classType.name);
//                }

                node.methodCall.functionSymbol = getFS(node.methodCall.functionName, classType.symbol.symbolTable);
                if (node.methodCall.functionSymbol == null) {
                    node.type = null;
                    errorListener.addError(node.position, "don't have such method");
/*
                node.methodCall.functionSymbol = resolveFunctionSymbol(node.methodCall.functionName, classType.symbol.symbolTable);
                if (node.methodCall.functionSymbol == null) {
                    node.type = null;
                    errorListener.addError(node.location, "don't have such method");
*/
                } else {
                    node.methodCall.type = node.methodCall.functionSymbol.returnType;
                    node.type = node.methodCall.type;
                    for (Expr e : node.methodCall.arguments)
                        e.accept(this);
                }
            } else {

                node.fieldAccess.symbol = getVS(node.fieldAccess.name, classType.symbol.symbolTable);
                if (node.fieldAccess.symbol == null) {
                    node.type = null;
                    errorListener.addError(node.position, "don't have such component");
/*
                node.fieldAccess.symbol = resolveVariableSymbol(node.fieldAccess.name, classType.symbol.symbolTable);
                if (node.fieldAccess.symbol == null) {
                    node.type = null;
                    errorListener.addError(node.location, "don't have such component");
*/
                } else {
                    node.fieldAccess.type = node.fieldAccess.symbol.variableType;
                    node.type = node.fieldAccess.type;
                }
            }

/*
            //leave();
*/
        }
    }

    @Override
    public void visit(UnaryExpr node) {
        node.expr.accept(this);
        node.type = node.expr.type;
    }

    private boolean isRelationOperation(String op) {
        return (op.equals("==") || op.equals(">") || op.equals("<") || op.equals(">=") || op.equals("<=") || op.equals("!="));
    }

    @Override
    public void visit(BinaryExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if (isRelationOperation(node.op)) {

            node.type = new PrimitiveType("bool", gSymT.getPrimitiveSymbol("bool"));
/*
            node.type = new PrimitiveType("bool", globalSymbolTable.getPrimitiveSymbol("bool"));
*/
        } else {
            node.type = node.lhs.type;
        }
    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);

        node.type = new PrimitiveType("void", gSymT.getPrimitiveSymbol("void"));
/*
        node.type = new PrimitiveType("void", globalSymbolTable.getPrimitiveSymbol("void"));
*/
    }

    @Override
    public void visit(EmptyStmt node) {

    }




    private void registerClass(ClassDecl node) {

        if (gSymT.getClassSymbol(node.name) != null || gSymT.getFS(node.name) != null) {
            errorListener.addError(node.position, "name confliction");
        } else {
            ClassSymbol classSymbol = new ClassSymbol(node.name,node.position,new SymbolTable(gSymT));

            node.symbol = classSymbol;

            gSymT.putClassSymbol(node.name, classSymbol);
/*
        if (globalSymbolTable.getClassSymbol(node.name) != null || globalSymbolTable.getFunctionSymbol(node.name) != null) {
            errorListener.addError(node.location, "name confliction");
        } else {
            ClassSymbol classSymbol = new ClassSymbol(node.name,node.location,new SymbolTable(globalSymbolTable));

            node.symbol = classSymbol;

            globalSymbolTable.putClassSymbol(node.name, classSymbol);
*/
            classHash.put(classSymbol.symbolTable, classSymbol);
        }
    }
    private boolean registerFunc_Error(FuncDecl node){


        if (nSymT.getFS(node.name) != null || nSymT.getVS(node.name) != null) {
            errorListener.addError(node.position, "Name confliction with func or var");
            return true;
        }

        if (nSymT.parent == null && gSymT.getClassSymbol(node.name) != null) {
            errorListener.addError(node.position, "Name confliction with class");
/*
        if (curSymbolTable.getFunctionSymbol(node.name) != null || curSymbolTable.getVariableSymbol(node.name) != null) {
            errorListener.addError(node.location, "Name confliction with func or var");
            return true;
        }

        if (curSymbolTable.parent == null && globalSymbolTable.getClassSymbol(node.name) != null) {
            errorListener.addError(node.location, "Name confliction with class");
*/
            return true;
        }
        return false;
    }
    private void registerFunc(FuncDecl node, ClassSymbol curClass) {
        if(registerFunc_Error(node)){
            return;
        }

        FuncSymbol functionSymbol = new FuncSymbol(node.position,(curClass != null ? curClass.name + "." : "") + node.name,getVT(node.returnType));
/*
        FuncSymbol functionSymbol = new FuncSymbol(node.location,(curClass != null ? curClass.name + "." : "") + node.name,resolveVariableType(node.returnType));
*/
//        System.out.println("register: " + functionSymbol.name);
        functionSymbol.Global = curClass == null;
        functionSymbol.funtionSymbolTable = null;

        if (curClass != null) {
            functionSymbol.parameterNames.add("this");
            functionSymbol.parameterTypes.add(new ClassType("", curClass));
        }
        for (VarDecl decl: node.parameters) {

            VarType type = getVT(decl.type);
/*
            VarType type = resolveVariableType(decl.type);
*/
            if(type == null)
                return ;
            functionSymbol.parameterTypes.add(type);
            functionSymbol.parameterNames.add(decl.name);
        }

        node.symbol = functionSymbol;


        nSymT.putFunc(node.name, functionSymbol);
/*
        curSymbolTable.putFunctionSymbol(node.name, functionSymbol);
*/
        //System.out.println(functionSymbol.name);
    }

    private void registerClassFunction(ClassDecl node) {

        ClassSymbol classSymbol = gSymT.getClassSymbol(node.name);



        classSymbol.symbolTable.parent = nSymT;
        nSymT = classSymbol.symbolTable;
/*
        ClassSymbol classSymbol = globalSymbolTable.getClassSymbol(node.name);

        enter(classSymbol.symbolTable);//+1
*/

        for (FuncDecl d : node.methods) {
            registerFunc(d, classSymbol);
        }

        registerFunc(node.construct, classSymbol);

        nSymT = nSymT.parent;
/*
        leave();//-1
*/
    }


    void checkother(VarDecl node){
        if (node.init != null) {
            node.init.accept(this);
        }
    }
    private boolean defineVariableChecker(VarDecl node){

        if (nSymT.getVS(node.name) != null || nSymT.getFS(node.name) != null) {
            errorListener.addError(node.position, "variable already defined");
            return false;
        } else if (nSymT.parent == null && gSymT.getClassSymbol(node.name) != null) {
            errorListener.addError(node.position, "same name class with var");
/*
        if (curSymbolTable.getVariableSymbol(node.name) != null || curSymbolTable.getFunctionSymbol(node.name) != null) {
            errorListener.addError(node.location, "variable already defined");
            return false;
        } else if (curSymbolTable.parent == null && globalSymbolTable.getClassSymbol(node.name) != null) {
            errorListener.addError(node.location, "same name class with var");
*/
            return false;
        }
        return true;
    }
    private void defineVariable(VarDecl node) {

        VarType type = getVT(node.type);
        checkother(node);
        if (type != null) {
            if (defineVariableChecker(node)){
                node.symbol = new VarSymbol(node.name, type,node.position, classHash.containsKey(nSymT), nSymT == gSymT);
                nSymT.putVar(node.name, node.symbol);
                if (node.init != null && node.symbol.isGlobalVariable) {
                    gSymT.globalInitVars.add(node.symbol);
/*
        VarType type = resolveVariableType(node.type);
        checkother(node);
        if (type != null) {
            if (defineVariableChecker(node)){
                node.symbol = new VarSymbol(node.name, type,node.location, classHash.containsKey(curSymbolTable), curSymbolTable == globalSymbolTable);
                curSymbolTable.putVariableSymbol(node.name, node.symbol);
                if (node.init != null && node.symbol.isGlobalVariable) {
                    globalSymbolTable.globalInitVars.add(node.symbol);
*/
                }
            }
        }
    }


    private void defineFunction(FuncDecl node, ClassSymbol classSymbol) {
        FuncSymbol functionSymbol = nSymT.getFS(node.name);
        nFunc = functionSymbol;
        functionSymbol.funtionSymbolTable = new SymbolTable(nSymT);

        functionSymbol.funtionSymbolTable.parent = nSymT;
        nSymT = functionSymbol.funtionSymbolTable;
/*
    private void enter(SymbolTable symbolTable) {
        symbolTable.parent = curSymbolTable;
        curSymbolTable = symbolTable;
    }

    private void leave() {
        curSymbolTable = curSymbolTable.parent;
    }

    private void defineFunction(FuncDecl node, ClassSymbol classSymbol) {
        FuncSymbol functionSymbol = curSymbolTable.getFunctionSymbol(node.name);
        curFunc = functionSymbol;
        functionSymbol.funtionSymbolTable = new SymbolTable(curSymbolTable);
        enter(functionSymbol.funtionSymbolTable);
*/

        if (classSymbol != null) {
            defineVariable(new VarDecl(new ClassTypeNode(classSymbol.name), "this", null));
        }
        for (VarDecl decl: node.parameters)
            defineVariable(decl);
        for (Stmt decl: node.body) {
            decl.accept(this);
        }


        nSymT = nSymT.parent;;
        nFunc = null;
    }

    private void defineClassFunction(ClassDecl node) {
        ClassSymbol classSymbol = gSymT.getClassSymbol(node.name);

        classSymbol.symbolTable.parent = nSymT;
        nSymT = classSymbol.symbolTable;

/*
        leave();
        curFunc = null;
    }

    private void defineClassFunction(ClassDecl node) {
        ClassSymbol classSymbol = globalSymbolTable.getClassSymbol(node.name);
        enter(classSymbol.symbolTable);
*/
        defineFunction(node.construct, classSymbol);
        for (FuncDecl d: node.methods) {
            defineFunction(d, classSymbol);
        }

        nSymT = nSymT.parent;;
    }
    private VarSymbol getVS (String name, SymbolTable symbolTable) {
        VarSymbol symbol = symbolTable.getVS(name);
/*
        leave();
    }
    private VarSymbol resolveVariableSymbol (String name, SymbolTable symbolTable) {
        VarSymbol symbol = symbolTable.getVariableSymbol(name);
*/
        if (symbol != null) {
            return symbol;
        } else {
            if (symbolTable.parent != null)

                return getVS(name, symbolTable.parent);
/*
                return resolveVariableSymbol(name, symbolTable.parent);
*/
            else {
                return null;
            }
        }
    }


    private VarType getVT(Type node) {
        if (node instanceof PrimitiveTypeNode) {
            PrimitiveSymbol symbol = gSymT.getPrimitiveSymbol(((PrimitiveTypeNode) node).name);
            if (symbol != null) {
                return new PrimitiveType(((PrimitiveTypeNode) node).name, symbol);
            } else {
                errorListener.addError(node.position, "there is no this type");
                return null;
            }
        } else if (node instanceof ClassTypeNode) {
            ClassSymbol symbol = gSymT.getClassSymbol(((ClassTypeNode) node).name);
            if (symbol != null) {
                return new ClassType(((ClassTypeNode) node).name, symbol);
            } else {
                errorListener.addError(node.position, "there is no this type");
/*
    private VarType resolveVariableType(Type node) {
        if (node instanceof PrimitiveTypeNode) {
            PrimitiveSymbol symbol = globalSymbolTable.getPrimitiveSymbol(((PrimitiveTypeNode) node).name);
            if (symbol != null) {
                return new PrimitiveType(((PrimitiveTypeNode) node).name, symbol);
            } else {
                errorListener.addError(node.location, "there is no this type");
                return null;
            }
        } else if (node instanceof ClassTypeNode) {
            ClassSymbol symbol = globalSymbolTable.getClassSymbol(((ClassTypeNode) node).name);
            if (symbol != null) {
                return new ClassType(((ClassTypeNode) node).name, symbol);
            } else {
                errorListener.addError(node.location, "there is no this type");
*/
                return null;
            }
        } else if (node instanceof  ArrayTypeNode) {
            ArrayTypeNode arrayTypeNode = (ArrayTypeNode) node;
            VarType baseType;
            if (arrayTypeNode.dim == 1) {

                baseType = getVT(((ArrayTypeNode) node).arraytype);
/*
                baseType = resolveVariableType(((ArrayTypeNode) node).arraytype);
*/
            } else {
                ArrayTypeNode newArray = new ArrayTypeNode();

                newArray.dim = arrayTypeNode.dim - 1;
                //   System.err.print(newArray.dim);

                newArray.position = arrayTypeNode.position;
                newArray.arraytype = arrayTypeNode.arraytype;
                baseType = getVT(newArray);
/*
                newArray.location = arrayTypeNode.location;
                newArray.arraytype = arrayTypeNode.arraytype;
                baseType = resolveVariableType(newArray);
*/
            }
            if(baseType != null) {
                return new ArrayType(baseType);
            } else
                return null;
        } else {
            assert  false;
            return null;
        }
    }




    private  FuncSymbol getFS(String name, SymbolTable symbolTable) {
//        System.out.println(name);
        FuncSymbol symbol = symbolTable.getFS(name);
/*
    private  FuncSymbol resolveFunctionSymbol(String name, SymbolTable symbolTable) {
//        System.out.println(name);
        FuncSymbol symbol = symbolTable.getFunctionSymbol(name);
*/
        if (symbol != null) {
//            if (symbol instanceof FuncSymbol)
            return symbol;
//            else
//                return null;
        } else {
            if (symbolTable.parent != null) {

                return getFS(name, symbolTable.parent);
/*
                return resolveFunctionSymbol(name, symbolTable.parent);
*/
            } else {
                return null;
            }
        }
    }

    @Override
    public void visit(AstProgram node) {
        for (ClassDecl d : node.classDecl) {
            registerClass(d);
        }
        if (errorListener.hasError())
            return;
        for (ClassDecl d : node.classDecl) {
            registerClassFunction(d);
        }
        if (errorListener.hasError())
            return;
        for (FuncDecl d : node.funcDecl) {
            registerFunc(d, null);
        }
        if (errorListener.hasError())
            return;

        for (ClassDecl d : node.classDecl) {

            ClassSymbol classSymbol = gSymT.getClassSymbol(d.name);

            classSymbol.symbolTable.parent = nSymT;
            nSymT = classSymbol.symbolTable;

            for (VarDecl decl: d.fields) {
                defineVariable(decl);
            }
            nSymT = nSymT.parent;;//Class Fields
/*
            ClassSymbol classSymbol = globalSymbolTable.getClassSymbol(d.name);
            enter(classSymbol.symbolTable);//+1
            for (VarDecl decl: d.fields) {
                defineVariable(decl);
            }
            leave();//Class Fields
*/
        }
        if (errorListener.hasError())
            return;
        for (Decl d : node.decl) {
            if (d instanceof VarDecl) {
                defineVariable((VarDecl)d);
            } else if (d instanceof ClassDecl) {
                defineClassFunction((ClassDecl)d);
            } else {
                defineFunction((FuncDecl)d, null);
            }
        }

    }
}
