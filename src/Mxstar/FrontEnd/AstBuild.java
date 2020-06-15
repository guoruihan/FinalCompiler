package Mxstar.FrontEnd;

import Mxstar.Ast.Statement.*;
import Mxstar.IR.Inst.Cdq;
import Mxstar.Symbol.*;
import Parser.*;
import Mxstar.ErrorProcessor.*;
import Parser.MxstarParser.*;
import Mxstar.Ast.*;
import Mxstar.Ast.Expr.*;

import java.util.LinkedList;
import java.util.List;

public class AstBuild extends MxstarBaseVisitor<Object> {
    private AstProgram astProgram;
    private CompileErrorListener errorListener;

    public AstBuild() {
        this.astProgram = new AstProgram();

        this.astProgram.position = new Position(0, 0);
        this.errorListener = new CompileErrorListener();
    }

    public AstBuild(CompileErrorListener errorListener) {
        this.astProgram = new AstProgram();

        this.astProgram.position = new Position(0, 0);
        this.errorListener = errorListener;
    }

    public AstProgram getAstProgram() {
        return astProgram;
    }




    @Override public JumpStmt visitJumpStatement(JumpStatementContext ctx) {
        JumpStmt jumpStmt= new JumpStmt();

        jumpStmt.position = new Position(ctx);
        if(ctx.RETURN() != null) {
            jumpStmt.isReturn = true;
            if (ctx.expr() != null)
                jumpStmt.retExpr = (Expr)ctx.expr().accept(this);
        } else
            jumpStmt.isReturn = false;
        jumpStmt.isBreak = ctx.BREAK() != null;
        return jumpStmt;
    }

    @Override public List<Stmt> visitVariableStmt(VariableStmtContext ctx) {
        List<Stmt> statements = new LinkedList<>();
        List<VarDecl> declarations = getVarDecl(ctx.varDecl());
        for(VarDecl c : declarations) {
            VarDeclStmt decl = new VarDeclStmt();

            decl.position = new Position(ctx);
            decl.declaration = c;
            statements.add(decl);
        };
        //System.out.println("mew12");
        //System.out.println(ctx.getText());
        //System.out.println(statements);
        return statements;
    }

    @Override public CondStmt visitConditionStatement(ConditionStatementContext ctx) {
        //System.out.println(ctx.getText());
        CondStmt conditionStmt = new CondStmt();

        conditionStmt.position = new Position(ctx);
        //System.out.println(conditionStmt.position);




        if(ctx.thenStatement.getText().length() == 1) {
            conditionStmt.thenStmt = null;
        } else {
            //System.out.println(ctx.statement().accept(this).getClass());
            //System.out.println(BlockStmt.class);
            if (ctx.thenStatement.accept(this).getClass() != BlockStmt.class) {
                BlockStmt tmp = new BlockStmt();
                //System.out.println(ctx.statement().accept(this).getClass());
                if (ctx.thenStatement.accept(this).getClass() == LinkedList.class) {

                    tmp.position = (((List<Stmt>) ctx.thenStatement.accept(this)).get(0)).position;
                    tmp.statements = ((List<Stmt>) ctx.thenStatement.accept(this));
                } else {
                    tmp.position = ((Stmt) ctx.thenStatement.accept(this)).position;

                    tmp.statements = new LinkedList<>();
                    tmp.statements.add((Stmt) ctx.thenStatement.accept(this));
                }
                conditionStmt.thenStmt = (Stmt) tmp;
            } else {
                conditionStmt.thenStmt = (Stmt) ctx.thenStatement.accept(this);
            }
        }

        //conditionStmt.thenStmt = (Stmt)ctx.thenStatement.accept(this);
        if (ctx.elseStatement != null) {

            if(ctx.elseStatement.getText().length() == 1) {
                conditionStmt.elseStmt = null;
            } else {
                //System.out.println(ctx.statement().accept(this).getClass());
                //System.out.println(BlockStmt.class);
                if (ctx.elseStatement.accept(this).getClass() != BlockStmt.class) {
                    BlockStmt tmp = new BlockStmt();
                    //System.out.println(ctx.statement().accept(this).getClass());
                    if (ctx.elseStatement.accept(this).getClass() == LinkedList.class) {

                        tmp.position = (((List<Stmt>) ctx.elseStatement.accept(this)).get(0)).position;
                        tmp.statements = ((List<Stmt>) ctx.elseStatement.accept(this));
                    } else {
                        tmp.position = ((Stmt) ctx.elseStatement.accept(this)).position;

                        tmp.statements = new LinkedList<>();
                        tmp.statements.add((Stmt) ctx.elseStatement.accept(this));
                    }
                    conditionStmt.elseStmt = (Stmt) tmp;
                } else {
                    conditionStmt.elseStmt = (Stmt) ctx.elseStatement.accept(this);
                }
            }
            //    conditionStmt.elseStmt = (Stmt) ctx.elseStatement.accept(this);
        }
        conditionStmt.expression = (Expr)ctx.expr().accept(this);
        return conditionStmt;
    }


    @Override public LoopStmt visitWhileExpr(WhileExprContext ctx) {
        LoopStmt loopStmt = new LoopStmt();

        loopStmt.position = new Position(ctx);

        loopStmt.startStmt = null;
        loopStmt.condition = (Expr) ctx.expr().accept(this);
        loopStmt.updateStmt = null;
        if(ctx.statement().getText().length() == 1) {
            loopStmt.body = null;
        } else {
            //System.out.println(ctx.statement().accept(this).getClass());
            //System.out.println(BlockStmt.class);
            if (ctx.statement().accept(this).getClass() != BlockStmt.class) {
                BlockStmt tmp = new BlockStmt();
                //System.out.println(ctx.statement().accept(this).getClass());
                if (ctx.statement().accept(this).getClass() == LinkedList.class) {

                    tmp.position = (((List<Stmt>) ctx.statement().accept(this)).get(0)).position;
                    tmp.statements = ((List<Stmt>) ctx.statement().accept(this));
                } else {
                    tmp.position = ((Stmt) ctx.statement().accept(this)).position;
                    tmp.statements = new LinkedList<>();
                    tmp.statements.add((Stmt) ctx.statement().accept(this));
                }
                loopStmt.body = (Stmt) tmp;
            } else {
                loopStmt.body = (Stmt) ctx.statement().accept(this);
            }
        }
        //loopStmt.body = (Stmt) ctx.statement().accept(this);
        return loopStmt;
    }

    @Override public LoopStmt visitForExpr(ForExprContext ctx) {
        LoopStmt loopStmt = new LoopStmt();
        if (ctx.initialize != null)
            loopStmt.startStmt = new ExprStmt((Expr) ctx.initialize.accept(this));
        if (ctx.step != null)
            loopStmt.updateStmt = new ExprStmt((Expr) ctx.step.accept(this));
        if (ctx.condition != null)
            loopStmt.condition = (Expr) ctx.condition.accept(this);
        //System.out.println("haha1");
        //System.out.println(ctx.statement().getText());
        //System.out.println((ctx.statement().accept(this)));
        //System.out.println(((String)ctx.statement().getText()).charAt(0));
        //if(((String)ctx.statement().getText()).charAt(0) == '{')
        //System.out.println( ctx.statement().accept(this));
        //System.out.println(ctx.statement().accept(this).getClass() == LinkedList.class);
        //System.out.println(ctx.getText());
        //System.out.println(ctx.statement().getText().length());
        if(ctx.statement().getText().length() == 1) {
            loopStmt.body = null;
        } else {
            //System.out.println(ctx.statement().accept(this).getClass());
            //System.out.println(BlockStmt.class);
            if (ctx.statement().accept(this).getClass() != BlockStmt.class) {
                BlockStmt tmp = new BlockStmt();
                //System.out.println(ctx.statement().accept(this).getClass());
                if (ctx.statement().accept(this).getClass() == LinkedList.class) {

                    tmp.position = (((List<Stmt>) ctx.statement().accept(this)).get(0)).position;
                    tmp.statements = ((List<Stmt>) ctx.statement().accept(this));
                } else {
                    tmp.position = ((Stmt) ctx.statement().accept(this)).position;
                    tmp.statements = new LinkedList<>();
                    tmp.statements.add((Stmt) ctx.statement().accept(this));
                }
                loopStmt.body = (Stmt) tmp;
            } else {
                loopStmt.body = (Stmt) ctx.statement().accept(this);
            }
        }
        /*System.out.println( (Stmt) (((List<Stmt>)ctx.statement().accept(this)).get(0)));
        System.out.println("ntag");
        loopStmt.body = (Stmt) ctx.statement().accept(this);*/
        //loopStmt.body = (Stmt) (((List<Stmt>)ctx.statement().accept(this)).get(0));
        //System.out.println("haha2");
        return loopStmt;
    }

    @Override public NewExpr visitNewExpr(NewExprContext ctx) {
        return (NewExpr)ctx.creator().accept(this);
    }

    @Override public UnaryExpr visitUnaryExpr(UnaryExprContext ctx) {
        UnaryExpr unaryExpr = new UnaryExpr();

        unaryExpr.position = new Position(ctx);
        if(ctx.postfix != null) {
            if(ctx.postfix.getText().equals("++")) {
                unaryExpr.op = "v++";
            } else {
                unaryExpr.op = "v--";
            }
        } else {
            if (ctx.prefix.getText().equals("++")) {
                unaryExpr.op = "++v";
            } else if(ctx.prefix.getText().equals("--")){
                unaryExpr.op = "--v";
            } else {
                unaryExpr.op =ctx.prefix.getText();
            }
        }
        unaryExpr.expr = (Expr) ctx.expr().accept(this);
        return unaryExpr;
    }

    @Override public Expr visitPrimaryExpr(PrimaryExprContext ctx) {
        if(ctx.Identifier() != null) {
            Identifier identifier = new Identifier();
            identifier.name = ctx.Identifier().getSymbol().getText();

            identifier.position = new Position(ctx);
//            System.out.println(identifier.position);
            return identifier;
        } else if (ctx.THIS() != null) {
            Identifier identifier = new Identifier();
            identifier.name = ctx.THIS().getSymbol().getText();

            identifier.position = new Position(ctx);
            return identifier;
        } else {
            return new LiteralExpr(ctx.token);
        }
    }

    @Override public Expr visitArrayExpr(ArrayExprContext ctx) {
        ArrayExpr expr = new ArrayExpr();

        expr.position = new Position(ctx);
        expr.expr = (Expr) ctx.expr(0).accept(this);
        expr.idx = (Expr) ctx.expr(1).accept(this);
        /*System.out.println(ctx.getText());
        System.out.println(ctx.expr(0).getText());
        System.out.println(ctx.expr(1).getText());*/
        /*if(!(expr.idx.type instanceof PrimitiveType)) {

            errorListener.addError(expr.idx.position, "idx must be integer");
        }*/
        if(expr.expr instanceof  NewExpr && ctx.expr(0).stop.getText().equals("]")) {
            errorListener.addError(expr.idx.position, "can not mess new a[n][i] with (new a[n])[i]");
        }
        return expr;
    }

    @Override public Expr visitMemberExpr(MemberExprContext ctx) {
        MembExpr expression = new MembExpr();

        expression.position = new Position(ctx);
        expression.object = (Expr) ctx.expr().accept(this);
        if(ctx.functionCall() != null) {
            expression.fieldAccess = null;
            expression.methodCall = visitFunctionCall(ctx.functionCall());
        } else {
            expression.methodCall = null;
            expression.fieldAccess = new Identifier(ctx.Identifier().getSymbol());
        }
        return expression;
    }

    @Override public BinaryExpr visitBinaryExpr(BinaryExprContext ctx) {
        BinaryExpr binExpr = new BinaryExpr();
        binExpr.lhs = (Expr)ctx.expr(0).accept(this);
        binExpr.rhs = (Expr)ctx.expr(1).accept(this);
        binExpr.op = ctx.bop.getText();

        binExpr.position = new Position(ctx);
        return  binExpr;
    }

    @Override public Expr visitSubExpr(SubExprContext ctx) {
        return (Expr)ctx.expr().accept(this);
    }

    @Override public AssignExpr visitAssignExpr(AssignExprContext ctx) {
        AssignExpr assignExpr = new AssignExpr();
        assignExpr.lhs = (Expr)ctx.expr(0).accept(this);

//        System.out.println(assignExpr.lhs.position + ":" + (assignExpr.lhs instanceof Identifier));
        assignExpr.rhs = (Expr)ctx.expr(1).accept(this);
        assignExpr.position = new Position(ctx);
        return assignExpr;
    }

    @Override public FuncCallExpr visitFuntionExpr(FuntionExprContext ctx) {
        return visitFunctionCall(ctx.functionCall());
    }

    @Override public FuncCallExpr visitFunctionCall(FunctionCallContext ctx) {
        FuncCallExpr funcCallExpr = new FuncCallExpr();
        funcCallExpr.functionName = ctx.Identifier().getSymbol().getText();
        funcCallExpr.arguments = new LinkedList<>();

        funcCallExpr.position = new Position(ctx);
        if (ctx.expr() != null) {
            for (ExprContext c : ctx.expr()) {
                funcCallExpr.arguments.add((Expr) c.accept(this));
                //System.out.print(((LinkedList<Expression>) funcCallExpr.arguments).getLast().);
            }
        }
        return funcCallExpr;
    }

    @Override public NewExpr visitInvalidCreater(InvalidCreaterContext ctx) {

        errorListener.addError(new Position(ctx), "can not resolve this new creator");
        return new NewExpr();
    }

    @Override public NewExpr visitValidCreater(ValidCreaterContext ctx) {
        NewExpr newExpr = new NewExpr();

        newExpr.position = new Position(ctx);
        newExpr.typeNode = visitVariableTypeBasic(ctx.variableTypeBasic());
        newExpr.exprDim = new LinkedList<>();
        if(ctx.expr() != null) {
            for(ExprContext c : ctx.expr()) {
                newExpr.exprDim.add((Expr)c.accept(this));
            }
        }
        if(ctx.empty() != null) {
            newExpr.emptyDim = ctx.empty().size();// fuck it
        } else {
            newExpr.emptyDim = 0;
        }
        return newExpr;
    }




    public Type whetherVoid(VarType1Context ctx) {
        if(ctx.VOID() != null) {
            return new PrimitiveTypeNode("void");
        } else {
            return visitVariableType(ctx.variableType());
        }
    }

    @Override public List<Stmt> visitBlock(BlockContext ctx) {
        //System.out.println("taginf");
        List<Stmt> statements = new LinkedList<>();
        if(ctx.statement() != null) {
            for(StatementContext c : ctx.statement()) {
                //System.out.println(c.getText());
                //System.out.println(c instanceof  VariableStmtContext);
                if (!(c instanceof EmptyStmtContext)){
                    if(c instanceof  VariableStmtContext){
                        statements.addAll(visitVariableStmt((VariableStmtContext) c));
                    } else {
                        statements.add((Stmt)c.accept(this));
                    }
                }
            }
        }
        //System.out.println("diff");
        return statements;
    }

    @Override public BlockStmt visitBlockStmt(BlockStmtContext ctx) {
        //System.out.println("tag3");
        BlockStmt blockStmt = new BlockStmt();

        blockStmt.position = new Position(ctx);
        blockStmt.statements = visitBlock(ctx.block());
        return blockStmt;
    }

    public VarDecl visitParameterDeclaration(ParaDeclContext ctx) {
        VarDecl VarDecl = new VarDecl((Type)ctx.variableType().accept(this),ctx.Identifier().getSymbol().getText());

        VarDecl.position = new Position(ctx);

        return VarDecl;
    }
    public FuncDecl visitFunctionDefinition(FuncDefContext ctx) {
        if(ctx.Identifier() == null) {

            errorListener.addError(new Position(ctx.getStart()),"invalid func name");

        }
        FuncDecl FuncDecl = new FuncDecl(whetherVoid(ctx.varType1()),ctx.Identifier().getSymbol().getText());

        FuncDecl.parameters = new LinkedList<>();
        if (ctx.paraDeclList() != null) {
            for (ParaDeclContext c : ctx.paraDeclList().paraDecl()) {
                FuncDecl.parameters.add(visitParameterDeclaration(c));
            }
        }

        FuncDecl.body = visitBlock(ctx.block());

        FuncDecl.position = FuncDecl.returnType.position;
        return FuncDecl;
    }

    @Override public FuncDecl visitConstructDefinition(ConstructDefinitionContext ctx) {
        if(ctx.Identifier() == null) {

            errorListener.addError(new Position(ctx.getStart()),"invalid class name");

        }
        FuncDecl FuncDecl = new FuncDecl(new PrimitiveTypeNode("void"),ctx.Identifier().getSymbol().getText());

        FuncDecl.parameters = new LinkedList<>();
        if (ctx.paraDeclList() != null) {
            for (ParaDeclContext c : ctx.paraDeclList().paraDecl()) {
                FuncDecl.parameters.add(visitParameterDeclaration(c));
            }
        }

        FuncDecl.body = visitBlock(ctx.block());

        FuncDecl.position = new Position(ctx);
        return FuncDecl;
    }
    public ClassDecl visitClassDefinition(ClassDefContext ctx) {
        ClassDecl ClassDecl = new ClassDecl(ctx.Identifier().getSymbol().getText());

        ClassDecl.position = new Position(ctx);

        for (MemDeclContext c: ctx.memDecl()) {
            if(c.varDecl() != null) {
                ClassDecl.fields.addAll(getVarDecl(c.varDecl()));
            }
            if(c.funcDef() != null) {
                FuncDecl tmp = visitFunctionDefinition(c.funcDef());
                if(tmp.name.equals(ClassDecl.name)) {

                    errorListener.addError(new Position(c), "there shouldn't have return val in construction");

                } else {
                    ClassDecl.methods.add(tmp);
                }
            }
            if(c.constructDefinition() != null) {
                if (!c.constructDefinition().Identifier().getSymbol().getText().equals(ClassDecl.name)) {

                    errorListener.addError(new Position(c), "construct definition without a same name");
                } else {
                    if (ClassDecl.construct != null) {
                        errorListener.addError(new Position(c), "too many construction function");
                    } else {

                        ClassDecl.construct = visitConstructDefinition(c.constructDefinition());
                    }
                }
            }
        }
        if (ClassDecl.construct == null) {
            ClassDecl.construct = FuncDecl.defaultConstructor(ClassDecl.name);
        }
        return ClassDecl;

    }

    @Override public Type visitVariableTypeBasic(VariableTypeBasicContext ctx) {
        if(ctx.INT() != null) {

            return new PrimitiveTypeNode("int",new Position(ctx));
        } else if (ctx.BOOL() != null) {
            return new PrimitiveTypeNode("bool",new Position(ctx));
        } else {
            return new ClassTypeNode(ctx.getText(),new Position(ctx));
        }
    }

    public Type visitVariableType(VariableTypeContext ctx) {
        if(ctx.empty().isEmpty()) {
            return visitVariableTypeBasic(ctx.variableTypeBasic());
        } else {

            ArrayTypeNode arrayType = new ArrayTypeNode(new Position(ctx),ctx.empty().size());
            /*System.out.println("haha");
            System.out.println(arrayType.dim);
            for(int i = 0;i<arrayType.dim;++i)
                System.out.println(ctx.empty(i));*/

            arrayType.arraytype = visitVariableTypeBasic(ctx.variableTypeBasic());
            return arrayType;
        }
    }

    public VarDecl visitVariableDefinition(VarDefContext ctx) {
        VarDecl VarDecl = new VarDecl();
        VarDecl.name = ctx.Identifier().getSymbol().getText();

        VarDecl.position = new Position(ctx);
        if(ctx.expr() != null) {
            VarDecl.init = (Expr) ctx.expr().accept(this);
        }
        return VarDecl;
    }

    public List<VarDecl> getVarDecl(VarDeclContext ctx) {

        List<VarDecl> declarations = new LinkedList<>();
        for(VarDefContext c : ctx.varList().varDef()) {
            declarations.add(visitVariableDefinition(c));
        }

        Type Type = visitVariableType(ctx.variableType());
        for(VarDecl c : declarations) {
            c.type = Type;
        }
        return declarations;
    }

    @Override public Object visitProgram(ProgramContext ctx) {
        for (DeclsContext c : ctx.decls()) {
            if(c.funcDef() != null) {
                astProgram.add(visitFunctionDefinition(c.funcDef()));

        }
            if(c.classDef() != null) {
                astProgram.add(visitClassDefinition(c.classDef()));
            }
            if(c.varDecl() != null) {
                astProgram.addAll(getVarDecl(c.varDecl()));
            }
        }
        return null;
    }

    @Override public ExprStmt visitExprStmt(ExprStmtContext ctx) {

        return new ExprStmt(new Position(ctx),(Expr) ctx.expr().accept(this));

    }

    @Override public CondStmt visitConditionStmt(ConditionStmtContext ctx) {
        return visitConditionStatement(ctx.conditionStatement());
    }

    @Override public LoopStmt visitLoopStmt(LoopStmtContext ctx) {
        return (LoopStmt)ctx.loopStatement().accept(this);
    }

    @Override public JumpStmt visitJumpStmt(JumpStmtContext ctx) {
        return visitJumpStatement(ctx.jumpStatement());
    }
}