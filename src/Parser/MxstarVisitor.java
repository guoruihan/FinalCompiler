// Generated from C:/Users/86186/Desktop/Final/src\Mxstar.g4 by ANTLR 4.8
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxstarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxstarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecls(MxstarParser.DeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxstarParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(MxstarParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarList(MxstarParser.VarListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#memDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemDecl(MxstarParser.MemDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constructDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructDefinition(MxstarParser.ConstructDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varType1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType1(MxstarParser.VarType1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#paraDeclList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaDeclList(MxstarParser.ParaDeclListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#paraDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaDecl(MxstarParser.ParaDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableType(MxstarParser.VariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#empty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty(MxstarParser.EmptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#variableTypeBasic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableTypeBasic(MxstarParser.VariableTypeBasicContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxstarParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(MxstarParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStmt(MxstarParser.ConditionStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStmt(MxstarParser.LoopStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableStmt(MxstarParser.VariableStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#conditionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStatement(MxstarParser.ConditionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExpr(MxstarParser.WhileExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForExpr}
	 * labeled alternative in {@link MxstarParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForExpr(MxstarParser.ForExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(MxstarParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpr(MxstarParser.SubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funtionExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuntionExpr(MxstarParser.FuntionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invalidCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvalidCreater(MxstarParser.InvalidCreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code validCreater}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidCreater(MxstarParser.ValidCreaterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MxstarParser.FunctionCallContext ctx);
}