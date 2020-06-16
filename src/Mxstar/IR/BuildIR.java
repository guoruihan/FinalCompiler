package Mxstar.IR;

import Mxstar.Ast.*;
import Mxstar.Ast.Expr.*;
import Mxstar.Ast.Statement.*;
import Mxstar.Configuration;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.Symbol.*;
import Mxstar.IR.Operand.*;

import static Mxstar.IR.Regs.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class BuildIR implements AstVisitor {

    public IRProgram irProgram;

    public BuildIR(GlobalSymbolTable gst) {
        this.globalSymbolTable = gst;
        this.irProgram = new IRProgram();
        this.functionMap = new HashMap<>();
        this.funcDeclarationMap = new HashMap<>();

        this.True = new HashMap<>();
        this.False= new HashMap<>();
        this.Result = new HashMap<>();
        this.Assign = new HashMap<>();

        this.loopAfterBB = new Stack<>();
        this.loopUpdBB = new Stack<>();

        this.ClassTag = false;
        this.ParaTag = false;

        Libfunc();
    }
    public Func curFunc;
    public BB curBB;
    private ClassSymbol curClassSymbol;
    private VirtualReg curThisPointer;
    //All we need to do, is to memorize where we are
    //不忘初心，方得始終

    private Stack<BB> loopUpdBB;
    private Stack<BB> loopAfterBB;
    private HashMap<Expr, Operand>  Result;
    private HashMap<Expr, Address> Assign;
    private HashMap<Expr, BB> True;
    private HashMap<Expr, BB> False;

    private static Func runall;
    private static Func external_malloc;
    private static Func library_stringGT;
    private static Func library_stringGE;
    private static Func library_stringLT;
    private static Func library_stringLE;
    private static Func library_stringEQ;
    private static Func library_stringNE;

    private static Func library_stringConcat;

    private GlobalSymbolTable globalSymbolTable;
    private boolean Tpri(VarType type){
        if(!(type instanceof PrimitiveType))
            return false;
        return true;
    }
    private boolean Tvoid(VarType type) {
        return Tpri(type) && ((PrimitiveType)type).name.equals("void");
    }
    private boolean Tint(VarType type) {
        return Tpri(type) && ((PrimitiveType)type).name.equals("int");
    }
    private boolean Tstr(VarType type) {
        return type instanceof ClassType &&((ClassType)type).name.equals("string");
    }
    private boolean Tbool(VarType type) {
        return Tpri(type) && ((PrimitiveType)type).name.equals("bool");
    }

    private HashMap<String, Func> functionMap;
    private HashMap<String, FuncDecl> funcDeclarationMap;

    public void visit(AstProgram node){
        //var,func,class

        for(VarDecl valDecl: node.globalVariables){

            ConstVal data = new ConstVal(valDecl.name, Configuration.REGISTER_WIDTH,1);
            VirtualReg virtualReg = new VirtualReg(valDecl.name);
            virtualReg.spillPlace = new AlloSpace(data,valDecl.name);
            irProgram.constVal.add(data);
            valDecl.symbol.VirtualReg = virtualReg;

        }

        LinkedList<FuncDecl> func = new LinkedList<>(node.funcDecl);
        //out class
        for (ClassDecl cd: node.classDecl) {
            if (cd.construct != null) {
                func.add(cd.construct);
            }
            func.addAll(cd.methods);
        }

        for (FuncDecl funcDeclaration: func) {
            funcDeclarationMap.put(funcDeclaration.symbol.name, funcDeclaration);
        }
        //in class

        for(FuncDecl funcDecl:func){
            if (functionMap.containsKey(funcDecl.symbol.name)){
                System.out.println("fuck off tag: 1");
                continue;
            }
            Func tmpfunc = new Func(Func.Type.UserDefined, funcDecl.symbol.name, !Tvoid(funcDecl.symbol.returnType));
            functionMap.put(funcDecl.symbol.name,tmpfunc);
        }

        for (FuncDecl funcDeclaration: node.funcDecl)
            funcDeclaration.accept(this);
        for (ClassDecl classDeclaration: node.classDecl)
            classDeclaration.accept(this);

        for (Func nfunc: functionMap.values()) {
            if (nfunc.type == Func.Type.UserDefined)
                nfunc.finishBuild();
        }


        //following is the initialization

        irProgram.funcs.add(runall);
        curFunc = runall;//we can call it runall
        runall.Global = new HashSet<>(globalSymbolTable.globalInitVars);
        BB enterBB = new BB(curFunc, "enterBB");
        curBB = curFunc.enterBB = enterBB;
        for (VarDecl d: node.globalVariables) {
            if (d.init != null)
                assignExpr(d.symbol.VirtualReg, d.init);
        }
        curBB.append(new Call(curBB, va0, functionMap.get("main")));//remember it's va0!

        curBB.append(new Return(curBB));
        curFunc.leaveBB = curBB;
        curFunc.finishBuild();
        //this means run the total function
        //to finish it later
    }
    private void Libfunc() {
        functionMap.put("print", new Func("print", false));
        functionMap.put("println", new Func("println", false));
        functionMap.put("printInt", new Func("printInt", false));
        functionMap.put("printlnInt", new Func("printlnInt", false));
        functionMap.put("getString", new Func("getString", true));
        functionMap.put("getInt", new Func("getInt", true));
        functionMap.put("toString", new Func("toString", true));
        functionMap.put("__lib_array_size", new Func("__lib_array_size", true));
        functionMap.put("string.length", new Func("string_length", true));
        functionMap.put("string.ord", new Func("string_ord", true));
        functionMap.put("string.parseInt", new Func("string_parseInt", true));
        functionMap.put("string.substring", new Func("string_substring", false));
        //need some change!



        library_stringGT= new Func(Func.Type.Library, "string_gt", true);
        library_stringGE= new Func(Func.Type.Library, "string_ge", true);
        library_stringLT= new Func(Func.Type.Library, "string_lt", true);
        library_stringLE= new Func(Func.Type.Library, "string_le", true);
        library_stringEQ= new Func(Func.Type.Library, "string_eq", true);
        library_stringNE= new Func(Func.Type.Library, "string_ne", true);

        library_stringConcat = new Func(Func.Type.Library, "string_add", true);
        external_malloc = new Func(Func.Type.External, "malloc", true);
        // we will meet in the future~

        runall = new Func(Func.Type.Library, "initabcd", true);

    }
    /*
    如何處理跳轉指令：
    1、True False 存储对应跳转块
    True.put(expr, trueBB);
    False.put(expr, falseBB);
    trueBB.append(new Move(trueBB, vr, new Imm(1)));
    falseBB.append(new Move(falseBB, vr, new Imm(0)));
    trueBB.append(new Jump(trueBB, mergeBB));
    falseBB.append(new Jump(falseBB, mergeBB));

        o(now)
       / \
(True)o   o(False)
       \ /
        o(merge)
    2、判断跳转结果
    expr.accept(this);
    curBB = mergeBB;
    我們的基礎套路！什麼逻辑跳转都要這麼做！
    */
    void boolAssign(Address vr, Expr expr) {
        BB trueBB = new BB(curFunc, "trueBB");
        BB falseBB = new BB(curFunc, "falseBB");
        BB mergeBB = new BB(curFunc, "mergeBB");


        True.put(expr, trueBB);  False.put(expr, falseBB);
        expr.accept(this);
        trueBB.append(new Move(trueBB, vr, new Imm(1)));
        falseBB.append(new Move(falseBB, vr, new Imm(0)));
        trueBB.append(new Jump(trueBB, mergeBB));
        falseBB.append(new Jump(falseBB, mergeBB));
        curBB = mergeBB;
    }

    private void assignExpr(Address vr, Expr expr ) {
        if (Tbool(expr.type)) {
            boolAssign(vr,expr);
        } else {
            Assign.put(expr, vr);
            expr.accept(this);
            Operand result = Result.get(expr);
            if (result != vr) {
                curBB.append(new Move(curBB, vr, result));
            }
        }
    }

    /***********************
     * 我們的主程序部分完成了
     * @param node
     */

    private boolean ClassTag;
    @Override
    public void visit(ClassDecl node) {
        curClassSymbol = node.symbol;
        ClassTag = true;
        if (node.construct != null)
            node.construct.accept(this);
        for (FuncDecl func: node.methods)
            func.accept(this);
        ClassTag = false;
    }

    private boolean ParaTag;
    @Override
    public void visit(FuncDecl node) {
        //System.err.println(node.name);
        curFunc = functionMap.get(node.symbol.name);
        curBB = curFunc.enterBB = new BB(curFunc, "enter_of_" + node.symbol.name);

        if (ClassTag) {
            VirtualReg vthis = new VirtualReg("");
            curFunc.parameters.add(vthis);
            curThisPointer = vthis;
        }

        ParaTag = true;

        //System.err.println(node.parameters.size());
        for (VarDecl variableDeclaration: node.parameters)
            variableDeclaration.accept(this);//定义传进来的参数，待填坑！
            //不對啊，我共用不就好了
        ParaTag = false;

        for (int i = 0; i < curFunc.parameters.size(); ++i) {
            if (i < 6) {
                curBB.append(new Move(curBB, curFunc.parameters.get(i), vargs.get(i)));
            } else {
                curBB.append(new Move(curBB, curFunc.parameters.get(i), curFunc.parameters.get(i).spillPlace));
            }
        }
        curFunc.Global.addAll(node.symbol.usedGlobalVariables);

        //System.err.println(curFunc.Global.size());
        if (Configuration.doGlobalAllocate) {
            for (VarSymbol variableSymbol : node.symbol.usedGlobalVariables) {
                curBB.append(new Move(curBB, variableSymbol.VirtualReg, variableSymbol.VirtualReg.spillPlace));
            }
        }

        for (Stmt statement: node.body) {
            statement.accept(this);
        }

        if (!(curBB.tail instanceof Return)) {
            if (Tvoid(node.symbol.returnType)) {
                curBB.append(new Return(curBB));
            } else {
                curBB.append(new Move(curBB, va0, new Imm(0)));
                //學到了va0是返回值的位置，但是具體還不清楚，所以暫時當做virreg實現
                curBB.append(new Return(curBB));
            }
        }

        LinkedList<Return> returnInst = new LinkedList<>();
        //System.err.println(curFunc.basicblocks.size());
        for (BB bb: curFunc.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                if (inst instanceof Return)
                    returnInst.add((Return) inst);
            }
        }

        BB leaveBB = new BB(curFunc, "leaveBB");
        for (Return ret: returnInst) {
            ret.prepend(new Jump(ret.BelongBB, leaveBB));
            ret.remove();
        }
        leaveBB.append(new Return(leaveBB));
        curFunc.leaveBB = leaveBB;

        //System.err.println(curFunc.basicblocks.size());
        if (Configuration.doGlobalAllocate) {
            Inst retInst = curFunc.leaveBB.tail;
            for (VarSymbol variableSymbol : node.symbol.usedGlobalVariables) {
                retInst.prepend(new Move(retInst.BelongBB, variableSymbol.VirtualReg.spillPlace, variableSymbol.VirtualReg));
            }
        }
        functionMap.put(node.symbol.name, curFunc);
        irProgram.funcs.add(curFunc);
        //System.err.println(curFunc.basicblocks.size());
    }

    @Override
    public void visit(VarDecl node) {
        VirtualReg VirtualReg = new VirtualReg(node.name);
        if (ParaTag) {
            if (curFunc.parameters.size() >= 6) {
                VirtualReg.spillPlace = new Mxstar.IR.Operand.Stack(VirtualReg.name);
            }
            curFunc.parameters.add(VirtualReg);
        }
        node.symbol.VirtualReg = VirtualReg;
        if (node.init != null) {
            assignExpr(VirtualReg, node.init);
        }
    }
    /***********************
     * class var 和 func，快樂結束
     * 下面是各種語句部分
     * @param node
     */


    @Override
    public void visit(BlockStmt node) {
        for (Stmt statement: node.statements)
            statement.accept(this);
    }

    @Override
    public void visit(LoopStmt node) {
        if (node.startStmt != null) {
            node.startStmt.accept(this);
        }
        BB bodyBB = new BB(curFunc, "bodyBB");
        BB condBB = node.condition == null ? bodyBB : new BB(curFunc, "condBB");
        BB afterBB = new BB(curFunc, "afterBB");
        BB updBB = node.updateStmt == null ? condBB : new BB(curFunc, "updBB");


        curBB.append(new Jump(curBB, condBB));
        loopUpdBB.push(updBB);//下一个周期跳到的地方
        loopAfterBB.push(afterBB);//循环结束跳到的地方
        //别tm再混了！
        if (node.condition != null) {
            True.put(node.condition, bodyBB);
            False.put(node.condition, afterBB);
            curBB = condBB;
            node.condition.accept(this);
        }
        curBB = bodyBB;
        if (node.body != null)
            node.body.accept(this);
        curBB.append(new Jump(curBB, updBB));

        if (node.updateStmt != null) {
            curBB = updBB;
            node.updateStmt.accept(this);
            curBB.append(new Jump(curBB, condBB));
        }

        curBB = afterBB;
        loopUpdBB.pop();
        loopAfterBB.pop();
    }

    @Override
    public void visit(JumpStmt node) {
        if (node.isBreak) {
            curBB.append(new Jump(curBB, loopAfterBB.peek()));
        } else if (node.isReturn) {

            if (node.retExpr != null) {
//                System.out.println(node.location);
                if (Tbool(node.retExpr.type)) {
                    boolAssign(va0, node.retExpr);
                } else {
                    node.retExpr.accept(this);
                    curBB.append(new Move(curBB, va0, Result.get(node.retExpr)));
                }
            }
            curBB.append(new Return(curBB));

        } else {
            curBB.append(new Jump(curBB, loopUpdBB.peek()));
        }
    }

    @Override
    public void visit(CondStmt node) {
        BB thenBB = new BB(curFunc, "thenBB");
        BB afterBB = new BB(curFunc, "afterBB");
        BB elseBB = node.elseStmt == null ? afterBB : new BB(curFunc,"elseBB");
        True.put(node.expression, thenBB);
        False.put(node.expression, elseBB);
        node.expression.accept(this);
        if(true) {
            curBB = thenBB;
            if(node.thenStmt != null) {
                node.thenStmt.accept(this);
            }
            curBB.append(new Jump(curBB, afterBB));
        }
        if (node.elseStmt != null) {
            curBB = elseBB;
            node.elseStmt.accept(this);
            curBB.append(new Jump(curBB, afterBB));
        }
        curBB = afterBB;
    }
    /***********************
     * logical finished
     */


    int String2Int(String str){
        return Integer.valueOf(str);
    }

    @Override
    public void visit(LiteralExpr node) {
        Operand operand;
        String type = node.typeName;
        if(type == "null"){
            operand = new Imm(0);
        } else{
            if(type == "int")
                operand = new Imm(String2Int(node.value));
            else {
                if(type == "bool"){
                    if (True.containsKey(node)) {
                        curBB.append(new Jump(curBB, node.value.equals("true") ? True.get(node) : False.get(node)));
                        return ;
                    }
                    else {
                        int nval = 0;
                        if(node.value.equals("true"))
                            nval = 1;
                        operand = new Imm(nval);
                    }
                } else {
                    ConstVal sd = new ConstVal("static_string", node.value.substring(1, node.value.length() - 1));
                    irProgram.constVal.add(sd);
                    operand = sd;
                }
            }
        }
        Result.put(node, operand);
    }

    @Override
    public void visit(Identifier node) {
        Operand operand;//指向那个位置
        if (node.name.equals("this")) {
            operand = curThisPointer;
        } else if (node.symbol.isClassField) {
            String fieldName = node.name;
            int offset = curClassSymbol.symbolTable.getOffset(fieldName);
            operand = new AlloSpace(curThisPointer, new Imm(offset));//连续申请空间！
        } else {
            operand = node.symbol.VirtualReg;
        }
        if (True.containsKey(node)) {
            curBB.append(new Cjump(curBB, operand, Cjump.CompareOP.NE, new Imm(0), True.get(node), False.get(node)));
        } else {
            Result.put(node, operand);
        }
    }


    @Override
    public void visit(ArrayExpr node) {
        node.expr.accept(this);
        Operand baseAddr = Result.get(node.expr);
        node.idx.accept(this);
        Operand idx = Result.get(node.idx);

        VirtualReg base;
        if (baseAddr instanceof VirtualReg) {
            base = (VirtualReg) baseAddr;
        } else{
            base = new VirtualReg("");
            curBB.append(new Move(curBB, base, baseAddr));
            //謹記都要看在不在reg裡面！
        }


        //連續空間就要多寫一個類了qwq
        //最後看看IR要不要分配空間呢

        AlloSpace memory;

        if (idx instanceof Imm) {
            memory = new AlloSpace(base, new Imm(((Imm)idx).value * Configuration.REGISTER_WIDTH + Configuration.REGISTER_WIDTH));//数组大小+存储大小的空间
        } else if (idx instanceof VirtualReg) {
            memory = new AlloSpace(base, (VirtualReg)idx, Configuration.REGISTER_WIDTH, new Imm(Configuration.REGISTER_WIDTH));
        } else if (idx instanceof AlloSpace) {
            VirtualReg VirtualReg = new VirtualReg("");
            curBB.append(new Move(curBB, VirtualReg, idx));//把内存中的内容读出来
            memory = new AlloSpace(base, VirtualReg, Configuration.REGISTER_WIDTH, new Imm(Configuration.REGISTER_WIDTH));
        } else {
            memory = null;
        }

        if (True.containsKey(node)) {
            curBB.append(new Cjump(curBB, memory, Cjump.CompareOP.NE, new Imm(0), True.get(node), False.get(node)));
        } else {
            Result.put(node, memory);
        }
    }


    @Override
    public void visit(FuncCallExpr node) {
        LinkedList<Operand> args = new LinkedList<>();
        if (!node.functionSymbol.Global)
            args.add(curThisPointer);
        for (int i = 0; i < node.arguments.size(); ++i) {
            Expr e = node.arguments.get(i);
            e.accept(this);
            args.add(Result.get(e));
        }

//        System.out.println(functionMap.get(node.functionSymbol.name));
//        System.out.println(node.functionSymbol.name);

        curBB.append(new Call(curBB, va0, functionMap.get(node.functionSymbol.name), args));

        if (True.containsKey(node)) {
            curBB.append(new Cjump(curBB, va0, Cjump.CompareOP.NE, new Imm(0), True.get(node), False.get(node)));
        } else {
            if (!Tvoid(node.functionSymbol.returnType)) {
                VirtualReg VirtualReg = new VirtualReg("");
                curBB.append(new Move(curBB, VirtualReg, va0));
                Result.put(node, VirtualReg);
            }
        }
    }


    private Operand allocateArray(LinkedList<Operand> dims, int baseBytes, Func constructor) {
        if (dims.size() == 0) {
            if (baseBytes == 0) {
                return new Imm(0);
            } else {
                VirtualReg retAddr = new VirtualReg("");
                curBB.append(new Call(curBB, va0, external_malloc, new Imm(baseBytes)));
                curBB.append(new Move(curBB, retAddr, va0));
                if (constructor != null) {
                    curBB.append(new Call(curBB, va0, constructor, retAddr));
                } else {
                    curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.ADD, retAddr, new Imm(Configuration.REGISTER_WIDTH)));
                    curBB.append(new Move(curBB, new AlloSpace(retAddr), new Imm(0)));
                    curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.SUB, retAddr, new Imm(Configuration.REGISTER_WIDTH)));
                }
                return retAddr;
            }
        } else {//new A [b]
            VirtualReg addr = new VirtualReg("");
            VirtualReg size = new VirtualReg("");
            VirtualReg bytes = new VirtualReg("");
            curBB.append(new Move(curBB, size, dims.get(0)));
            curBB.append(new BinaryInst(curBB,BinaryInst.BinaryOp.SUB,bytes,bytes));
            curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.ADD,bytes,new Imm(Configuration.REGISTER_WIDTH )));
            curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.MUL,bytes,size));
            curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.ADD,bytes,new Imm(Configuration.REGISTER_WIDTH )));
            //curBB.append(new Li(curBB, bytes, new AlloSpace(size, Configuration.REGISTER_WIDTH, new Imm(Configuration.REGISTER_WIDTH))));
            curBB.append(new Call(curBB, va0, external_malloc, bytes));
            curBB.append(new Move(curBB, addr, va0));
            curBB.append(new Move(curBB, new AlloSpace(addr), size));

            BB condBB = new BB(curFunc,"condBB" );
            BB bodyBB = new BB(curFunc, "bodyBB");
            BB afterBB = new BB(curFunc, "afterBB");

            curBB.append(new Jump(curBB, condBB));
            condBB.append(new Cjump(condBB, size, Cjump.CompareOP.GT, new Imm(0), bodyBB, afterBB));
            curBB = bodyBB;

            LinkedList<Operand> restDimensions = new LinkedList<>(dims);
            restDimensions.removeFirst();
            Operand pointer = allocateArray(restDimensions, baseBytes, constructor);
            curBB.append(new Move(curBB, new AlloSpace(addr, size, Configuration.REGISTER_WIDTH), pointer));

            curBB.append(new UnaryInst(curBB, UnaryInst.UnaryOp.DEC, size));
            curBB.append(new Jump(curBB, condBB));
            curBB = afterBB;
            return addr;
        }
    }

    @Override
    public void visit(NewExpr node) {
        Func constructor;
        if(!(node.type instanceof ClassType)){
            constructor = null;
        } else {
            ClassType classType = (ClassType)(node.type);
            if (classType.name.equals("string") || classType.symbol.symbolTable.getFS(classType.name) == null) {
                constructor = null;
            } else {

                constructor = functionMap.get(classType.name + "." + classType.name);
                if (constructor == null) {
                    System.out.println("!!!there's no " + classType.name);
                }
            }
        }

        //constructed

        LinkedList<Operand> dims = new LinkedList<>();
        for (Expr nexpr: node.exprDim) {
            nexpr.accept(this);
            dims.add(Result.get(nexpr));
        }


        if (node.emptyDim > 0 || node.typeNode instanceof PrimitiveTypeNode) {
            Operand pointer = allocateArray(dims, 0 , constructor);
            Result.put(node, pointer);
        } else {
            int bytes;
            if (node.type instanceof ClassType && ((ClassType)node.type).name.equals("string"))
                bytes = Configuration.REGISTER_WIDTH * 2;//'/0'
            else
                bytes = node.type.getBytes()* (node.emptyDim + 1);
                //bytes = node.type.getBytes() * (node.emptyDim + 1);
            Operand pointer = allocateArray(dims, bytes, constructor);
            Result.put(node, pointer);
        }
        //其实我们不用构造空间，mem部分只要有这么个名号就行了
        //憨包，到codegen要还的啊
    }


    @Override
    public void visit(MembExpr node) {//成员
        VirtualReg baseAddr = new VirtualReg("");
        node.object.accept(this);//先搞左边
        curBB.append(new Move(curBB, baseAddr, Result.get(node.object)));

        if (node.object.type instanceof ArrayType) {
            if(node.methodCall.functionName.equals("size")){
                Func func = functionMap.get("__lib_array_size");

                curBB.append(new Call(curBB, va0, func, Result.get(node.object)));

                VirtualReg retvalue = new VirtualReg("");
                curBB.append(new Move(curBB, retvalue, va0));
                Result.put(node, retvalue);
            } else {
                Result.put(node, new AlloSpace(baseAddr));//无容身之所qwq
            }
        } else {//classType
            ClassType classType = (ClassType)node.object.type;
            Operand operand;
            if (node.fieldAccess != null) {//访问值
                operand = new AlloSpace(baseAddr, new Imm(classType.symbol.symbolTable.getOffset(node.fieldAccess.name)));
            } else {//调用函数
                Func func = functionMap.get(node.methodCall.functionSymbol.name);
                //君の名は
                LinkedList<Operand> args = new LinkedList<>();
                args.add(baseAddr);
                for (Expr expression: node.methodCall.arguments) {
                    expression.accept(this);
                    args.add(Result.get(expression));
                }
                curBB.append(new Call(curBB, va0, func, args));
                //after run
                if (!Tvoid(node.methodCall.functionSymbol.returnType)) {
                    VirtualReg retvalue = new VirtualReg("");
                    curBB.append(new Move(curBB, retvalue, va0));
                    operand = retvalue;
                } else {
                    operand = null;
                }
                //ret
            }

            if (True.containsKey(node)) {
                curBB.append(new Cjump(curBB, operand, Cjump.CompareOP.NE, new Imm(0), True.get(node), False.get(node)));
            } else {
                Result.put(node, operand);
            }
        }
    }

/*shigong tag*/
    @Override
    public void visit(UnaryExpr node) {
        if (node.op.equals("!")) {
            if(False.get(node) != null) {
                True.put(node.expr, False.get(node));
                False.put(node.expr, True.get(node));
            }
            node.expr.accept(this);
        }
        node.expr.accept(this);
        Operand operand = Result.get(node.expr);


        UnaryInst.UnaryOp nop;
        if(node.op.equals("v++") || node.op.equals("++v")) nop = UnaryInst.UnaryOp.INC; else {
            if (node.op.equals("v--") || node.op.equals("--v")) nop = UnaryInst.UnaryOp.DEC; else {
                if (node.op.equals("-")) nop= UnaryInst.UnaryOp.NEG; else {
                    if (node.op.equals("~")) nop= UnaryInst.UnaryOp.NOT; else {
                        nop=UnaryInst.UnaryOp.LOGICNEG;
                        //actually, useless
                    }
                }
            }
        }
        switch (node.op) {
            case "v++": case "v--": {
                VirtualReg val = new VirtualReg("val");
                curBB.append(new Move(curBB, val, operand));
                curBB.append(new UnaryInst(curBB, nop, (Address)operand));
                Result.put(node, val);
                break;
            }
            case "++v": case "--v": {
                curBB.append(new UnaryInst(curBB, nop, (Address)operand));
                Result.put(node, operand);
                break;
            }
            case "-": case "~": {
                VirtualReg val = new VirtualReg("val");
                curBB.append(new Move(curBB, val, operand));
                curBB.append(new UnaryInst(curBB, nop, val));
                Result.put(node, val);
                break;
            }
            default: {
                VirtualReg val = new VirtualReg("val");
                curBB.append(new Move(curBB, val, operand));
                curBB.append(new UnaryInst(curBB, nop, val));
                Result.put(node, operand);
            }
        }
    }
//四大天王
    public Operand doStringConcat(Expr lhs, Expr rhs) {
        Address result = new VirtualReg("");
        lhs.accept(this);
        Operand rlhs = Result.get(lhs);
        rhs.accept(this);

        Operand rrhs = Result.get(rhs);
        VirtualReg VirtualReg;
        //if (rlhs instanceof AlloSpace && !(rlhs instanceof StackSlot)) {
        if (rlhs instanceof AlloSpace) {
            VirtualReg = new VirtualReg("");
            curBB.append(new Move(curBB, VirtualReg, rlhs));
            rlhs = VirtualReg;
        }
        //if (rrhs instanceof AlloSpace && !(rrhs instanceof StackSlot)) {
        if (rrhs instanceof AlloSpace) {
            VirtualReg = new VirtualReg("");
            curBB.append(new Move(curBB, VirtualReg, rrhs));
            rrhs = VirtualReg;
        }
        curBB.append(new Call(curBB, va0, library_stringConcat, rlhs, rrhs));
        curBB.append(new Move(curBB, result, va0));
        return result;
    }

    BinaryInst.BinaryOp getop(String op){
        if(op.equals("*")) return BinaryInst.BinaryOp.MUL;
        if(op.equals("%")) return BinaryInst.BinaryOp.REM;
        if(op.equals("/")) return BinaryInst.BinaryOp.DIV;
        if(op.equals("+")) return BinaryInst.BinaryOp.ADD;
        if(op.equals("-")) return BinaryInst.BinaryOp.SUB;
        if(op.equals("<<")) return BinaryInst.BinaryOp.SLL;
        if(op.equals(">>")) return BinaryInst.BinaryOp.SRL;
        if(op.equals("|")) return BinaryInst.BinaryOp.OR;
        if(op.equals("&")) return BinaryInst.BinaryOp.AND;
        return BinaryInst.BinaryOp.XOR;
    }

    public Operand doArithCalc(String op, Address dest, Expr lhs, Expr rhs) {
        Address result = new VirtualReg("");
        lhs.accept(this);
        Operand rlhs = Result.get(lhs);
        rhs.accept(this);
        Operand rrhs = Result.get(rhs);

        BinaryInst.BinaryOp bop = null;
        boolean isReverseAble = true;
        if (op.equals("<<") || op.equals(">>")||op.equals("-")||op.equals("/") ||op.equals("%") ||op.equals("*") )
            isReverseAble = false;
        bop = getop(op);
        if (rlhs == dest) {
            result = dest;
            if (op.equals("<<") || op.equals(">>")) {
                curBB.append(new Move(curBB, vt4, rrhs));
                curBB.append(new BinaryInst(curBB, bop, result, vt4));
            } else {
                curBB.append(new BinaryInst(curBB, bop, result, rrhs));
            }
        } else if (isReverseAble && rrhs == dest) {
            result = dest;
            curBB.append(new BinaryInst(curBB, bop, result, rlhs));
        } else {
            if (op.equals("<<") || op.equals(">>")) {
                curBB.append(new Move(curBB, result, rlhs));
                curBB.append(new Move(curBB, vt4, rrhs));
                curBB.append(new BinaryInst(curBB, bop, result, vt4));
            } else {
                curBB.append(new Move(curBB, result, rlhs));
                curBB.append(new BinaryInst(curBB, bop, result, rrhs));
            }
        }
        return result;
    }

    public void doLogicCalc(String op, Expr lhs, Expr rhs, BB trueBB, BB falseBB) {
        BB checkBB = new BB(curFunc, "secondCondBB");
        if (op.equals("&&")) {
            False.put(lhs, falseBB);
            True.put(lhs, checkBB);
        } else {
            False.put(lhs, checkBB);
            True.put(lhs, trueBB);
        }
        lhs.accept(this);
        curBB = checkBB;
        True.put(rhs, trueBB);
        False.put(rhs, falseBB);
        rhs.accept(this);
    }

    /*算数部分结束啦？天真*/


    public void doCompCalc(String op, Expr lhs, Expr rhs, BB trueBB, BB falseBB) {
        if (trueBB == null)
            return ;
        lhs.accept(this);
        Operand rlhs = Result.get(lhs);
        rhs.accept(this);

        Operand rrhs = Result.get(rhs);
        Cjump.CompareOP cop = null;
        switch (op) {
            case ">": cop = Cjump.CompareOP.GT; break;
            case ">=": cop = Cjump.CompareOP.GE; break;
            case "<": cop = Cjump.CompareOP.LT; break;
            case "<=": cop = Cjump.CompareOP.LE; break;
            case "==": cop = Cjump.CompareOP.EQ; break;
            case "!=": cop = Cjump.CompareOP.NE; break;
        }

        if (lhs.type instanceof ClassType && ((ClassType)lhs.type).name.equals("string")) {
            VirtualReg result = new VirtualReg("");




            switch (op) {
                case ">": curBB.append(new Call(curBB, va0, library_stringGT, rlhs, rrhs)); break;
                case ">=": curBB.append(new Call(curBB, va0, library_stringGE, rlhs, rrhs)); break;
                case "<": curBB.append(new Call(curBB, va0, library_stringLT, rlhs, rrhs)); break;
                case "<=": curBB.append(new Call(curBB, va0, library_stringLE, rlhs, rrhs)); break;
                case "==": curBB.append(new Call(curBB, va0, library_stringEQ, rlhs, rrhs)); break;
                case "!=": curBB.append(new Call(curBB, va0, library_stringNE, rlhs, rrhs)); break;
            }

            curBB.append(new Move(curBB, result, va0));
            curBB.append(new Cjump(curBB, result, cop, new Imm(0), trueBB, falseBB));
        } else {
            if (rlhs instanceof AlloSpace && rrhs instanceof AlloSpace) {
                VirtualReg VirtualReg = new VirtualReg("");
                curBB.append(new Move(curBB, VirtualReg, rlhs));
                rlhs = VirtualReg;
            }
            curBB.append(new Cjump(curBB, rlhs, cop, rrhs, trueBB, falseBB));
        }
    }

    @Override
    public void visit(BinaryExpr node) {
        // link,arith,comp,logic
        switch (node.op) {
            case "*": case "/": case "+": case "-": case "%": case "&": case "|": case "^": case"<<": case ">>": {
                if (node.op.equals("+") && Tstr(node.type)) {
                    Result.put(node, doStringConcat(node.lhs, node.rhs));
                } else {
                    Result.put(node, doArithCalc(node.op, Assign.get(node), node.lhs, node.rhs));
                }
                break;
            }
            case "<": case ">": case "<=": case ">=": case"==": case "!=":case "&&": case "||": {
                if (!True.containsKey(node)) {
                    VirtualReg ret = new VirtualReg("");
                    BB TrueBB = new BB(curFunc, "trueBB");
                    BB FalseBB = new BB(curFunc, "falseBB");
                    BB AfterBB = new BB(curFunc, "afterBB");
                    True.put(node, TrueBB);
                    False.put(node, FalseBB);
                    if(node.op .equals("&&") || node.op.equals("||")) {
                        doLogicCalc(node.op, node.lhs, node.rhs, True.get(node), False.get(node));
                    } else {
                        doCompCalc(node.op, node.lhs, node.rhs, True.get(node), False.get(node));
                    }

                    curBB = TrueBB;
                    curBB.append(new Move(curBB, ret, new Imm(1)));
                    curBB.append(new Jump(curBB, AfterBB));
                    curBB = FalseBB;
                    curBB.append(new Move(curBB, ret, new Imm(0)));
                    curBB.append(new Jump(curBB, AfterBB));
                    curBB = AfterBB;
                    Result.put(node, ret);
                } else {
                    if(node.op.equals("&&") || node.op.equals("||")){
                        doLogicCalc(node.op, node.lhs, node.rhs, True.get(node), False.get(node));
                    } else {
                        doCompCalc(node.op, node.lhs, node.rhs, True.get(node), False.get(node));
                    }
                }
                break;
            }
        }
    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        Operand lval = Result.get(node.lhs);
//        assert  lval instanceof Address;
        assignExpr((Address)lval, node.rhs);
    }


    //下面是一些多餘的節點的遺留問題
    @Override
    public void visit(VarDeclStmt node) {
        node.declaration.accept(this);
    }

    @Override
    public void visit(ExprStmt node) {
        node.expression.accept(this);
    }


    //下面是一些我們稱作“歷史遺留問題”的內容
    //當我們選擇了從先人的手中繼承遺產
    //便要承受他們的存在帶來的歷史代價
    //沒有什麼憑空產生，自然也不會憑空消散
    //只是讓他們靜默在時代的底層，逐漸為人們忘卻
    //每個時代都不是獨立的，有自己的pre，也有自己的next
    //可這都是宏觀敘事的觀點
    //我一個小小的開發者，都厭煩數月前自己的冗餘
    //請問大眾與大眾之間，又怎麼會有完美的理解
    //但還是希望我們的初心能夠傳遞下去
    //哪怕是一個抽象類
    //也會在最終的故事裡
    //留下自己的一筆
    //我的名字也會在下面
    @Override
    public void visit(Decl node) {}
    @Override
    public void visit(Type node) {}
    @Override
    public void visit(ArrayType node) {}
    @Override
    public void visit(PrimitiveTypeNode node) {}
    @Override
    public void visit(ClassTypeNode node) {}
    @Override
    public void visit(Stmt node) {}
    @Override
    public void visit(Expr node) {}
    @Override
    public void visit(EmptyStmt node) {}
    /*********************************************************
     * @Override
     * public void RuihanGuo(){System.out.println("see you~");}
     *********************************************************/
}
