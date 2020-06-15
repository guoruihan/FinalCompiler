// Generated from C:/Users/86186/Desktop/Final/src\Mxstar.g4 by ANTLR 4.8
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterDecls(MxstarParser.DeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitDecls(MxstarParser.DeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxstarParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxstarParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(MxstarParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(MxstarParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varList}.
	 * @param ctx the parse tree
	 */
	void enterVarList(MxstarParser.VarListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varList}.
	 * @param ctx the parse tree
	 */
	void exitVarList(MxstarParser.VarListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#memDecl}.
	 * @param ctx the parse tree
	 */
	void enterMemDecl(MxstarParser.MemDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#memDecl}.
	 * @param ctx the parse tree
	 */
	void exitMemDecl(MxstarParser.MemDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constructDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstructDefinition(MxstarParser.ConstructDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constructDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstructDefinition(MxstarParser.ConstructDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varType1}.
	 * @param ctx the parse tree
	 */
	void enterVarType1(MxstarParser.VarType1Context ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varType1}.
	 * @param ctx the parse tree
	 */
	void exitVarType1(MxstarParser.VarType1Context ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#paraDeclList}.
	 * @param ctx the parse tree
	 */
	void enterParaDeclList(MxstarParser.ParaDeclListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#paraDeclList}.
	 * @param ctx the parse tree
	 */
	void exitParaDeclList(MxstarParser.ParaDeclListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#paraDecl}.
	 * @param ctx the parse tree
	 */
	void enterParaDecl(MxstarParser.ParaDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#paraDecl}.
	 * @param ctx the parse tree
	 */
	void exitParaDecl(MxstarParser.ParaDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(MxstarParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(MxstarParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#empty}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(MxstarParser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#empty}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(MxstarParser.EmptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#variableTypeBasic}.
	 * @param ctx the parse tree
	 */
	void enterVariableTypeBasic(MxstarParser.VariableTypeBasicContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#variableTypeBasic}.
	 * @param ctx the parse tree
	 */
	void exitVariableTypeBasic(MxstarParser.VariableTypeBasicContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxstarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxstarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(MxstarParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(MxstarParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterConditionStmt(MxstarParser.ConditionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitConditionStmt(MxstarParser.ConditionStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStmt(MxstarParser.LoopStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStmt(MxstarParser.LoopStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableStmt(MxstarParser.VariableStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableStmt(MxstarParser.VariableStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionStatement(MxstarParser.ConditionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionStatement(MxstarParser.ConditionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileExpr(MxstarParser.WhileExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileExpr(MxstarParser.WhileExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterForExpr(MxstarParser.ForExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitForExpr(MxstarParser.ForExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(MxstarParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(MxstarParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(MxstarParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(MxstarParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funtionExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuntionExpr(MxstarParser.FuntionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funtionExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuntionExpr(MxstarParser.FuntionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invalidCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterInvalidCreater(MxstarParser.InvalidCreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invalidCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitInvalidCreater(MxstarParser.InvalidCreaterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code validCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterValidCreater(MxstarParser.ValidCreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code validCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitValidCreater(MxstarParser.ValidCreaterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MxstarParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MxstarParser.FunctionCallContext ctx);
}