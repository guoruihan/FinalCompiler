// Generated from C:/Users/86186/Desktop/Final/src\Mxstar.g4 by ANTLR 4.8
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, BOOL=35, INT=36, STRING=37, VOID=38, IF=39, 
		ELSE=40, FOR=41, WHILE=42, BREAK=43, CONTINUE=44, RETURN=45, NEW=46, CLASS=47, 
		THIS=48, Integer_Literal=49, NULL_Literal=50, String_Literal=51, Bool_Literal=52, 
		Identifier=53, WhiteSpaces=54, NewLines=55, LineComment=56, BlockComment=57;
	public static final int
		RULE_program = 0, RULE_decls = 1, RULE_funcDef = 2, RULE_classDef = 3, 
		RULE_varDecl = 4, RULE_varList = 5, RULE_varDef = 6, RULE_memDecl = 7, 
		RULE_constructDefinition = 8, RULE_varType1 = 9, RULE_paraDeclList = 10, 
		RULE_paraDecl = 11, RULE_variableType = 12, RULE_empty = 13, RULE_variableTypeBasic = 14, 
		RULE_block = 15, RULE_statement = 16, RULE_conditionStatement = 17, RULE_jumpStatement = 18, 
		RULE_loopStatement = 19, RULE_expr = 20, RULE_creator = 21, RULE_functionCall = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "decls", "funcDef", "classDef", "varDecl", "varList", "varDef", 
			"memDecl", "constructDefinition", "varType1", "paraDeclList", "paraDecl", 
			"variableType", "empty", "variableTypeBasic", "block", "statement", "conditionStatement", 
			"jumpStatement", "loopStatement", "expr", "creator", "functionCall"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "';'", "','", "'='", "'['", "']'", 
			"'.'", "'++'", "'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", 
			"'>>'", "'<<'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", "'&'", 
			"'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'bool'", "'int'", "'string'", 
			"'void'", "'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", 
			"'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "BOOL", 
			"INT", "STRING", "VOID", "IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", 
			"RETURN", "NEW", "CLASS", "THIS", "Integer_Literal", "NULL_Literal", 
			"String_Literal", "Bool_Literal", "Identifier", "WhiteSpaces", "NewLines", 
			"LineComment", "BlockComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxstarParser.EOF, 0); }
		public List<DeclsContext> decls() {
			return getRuleContexts(DeclsContext.class);
		}
		public DeclsContext decls(int i) {
			return getRuleContext(DeclsContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << Identifier))) != 0)) {
				{
				{
				setState(46);
				decls();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclsContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decls);
		try {
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				funcDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				classDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				varDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public VarType1Context varType1() {
			return getRuleContext(VarType1Context.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParaDeclListContext paraDeclList() {
			return getRuleContext(ParaDeclListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			varType1();
			setState(60);
			match(Identifier);
			setState(61);
			match(T__0);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) {
				{
				setState(62);
				paraDeclList();
				}
			}

			setState(65);
			match(T__1);
			setState(66);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MxstarParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public List<MemDeclContext> memDecl() {
			return getRuleContexts(MemDeclContext.class);
		}
		public MemDeclContext memDecl(int i) {
			return getRuleContext(MemDeclContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(CLASS);
			setState(69);
			match(Identifier);
			setState(70);
			match(T__2);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
				{
				{
				setState(71);
				memDecl();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(T__3);
			setState(78);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public VarListContext varList() {
			return getRuleContext(VarListContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			variableType();
			setState(81);
			varList();
			setState(82);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarListContext extends ParserRuleContext {
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public VarListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarListContext varList() throws RecognitionException {
		VarListContext _localctx = new VarListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			varDef();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(85);
				match(T__5);
				setState(86);
				varDef();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(Identifier);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(93);
				match(T__6);
				setState(94);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemDeclContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public ConstructDefinitionContext constructDefinition() {
			return getRuleContext(ConstructDefinitionContext.class,0);
		}
		public MemDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterMemDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitMemDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMemDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemDeclContext memDecl() throws RecognitionException {
		MemDeclContext _localctx = new MemDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_memDecl);
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				funcDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				varDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				constructDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParaDeclListContext paraDeclList() {
			return getRuleContext(ParaDeclListContext.class,0);
		}
		public ConstructDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConstructDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConstructDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConstructDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructDefinitionContext constructDefinition() throws RecognitionException {
		ConstructDefinitionContext _localctx = new ConstructDefinitionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constructDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(Identifier);
			setState(103);
			match(T__0);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) {
				{
				setState(104);
				paraDeclList();
				}
			}

			setState(107);
			match(T__1);
			setState(108);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarType1Context extends ParserRuleContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(MxstarParser.VOID, 0); }
		public VarType1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarType1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarType1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarType1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarType1Context varType1() throws RecognitionException {
		VarType1Context _localctx = new VarType1Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_varType1);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case STRING:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				variableType();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDeclListContext extends ParserRuleContext {
		public List<ParaDeclContext> paraDecl() {
			return getRuleContexts(ParaDeclContext.class);
		}
		public ParaDeclContext paraDecl(int i) {
			return getRuleContext(ParaDeclContext.class,i);
		}
		public ParaDeclListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDeclList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterParaDeclList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitParaDeclList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitParaDeclList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaDeclListContext paraDeclList() throws RecognitionException {
		ParaDeclListContext _localctx = new ParaDeclListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paraDeclList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			paraDecl();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(115);
				match(T__5);
				setState(116);
				paraDecl();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDeclContext extends ParserRuleContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ParaDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterParaDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitParaDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitParaDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaDeclContext paraDecl() throws RecognitionException {
		ParaDeclContext _localctx = new ParaDeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_paraDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			variableType();
			setState(123);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeContext extends ParserRuleContext {
		public VariableTypeBasicContext variableTypeBasic() {
			return getRuleContext(VariableTypeBasicContext.class,0);
		}
		public List<EmptyContext> empty() {
			return getRuleContexts(EmptyContext.class);
		}
		public EmptyContext empty(int i) {
			return getRuleContext(EmptyContext.class,i);
		}
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVariableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVariableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			variableTypeBasic();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(126);
				match(T__7);
				setState(127);
				empty();
				setState(128);
				match(T__8);
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyContext extends ParserRuleContext {
		public EmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyContext empty() throws RecognitionException {
		EmptyContext _localctx = new EmptyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_empty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeBasicContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MxstarParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MxstarParser.STRING, 0); }
		public TerminalNode BOOL() { return getToken(MxstarParser.BOOL, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public VariableTypeBasicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableTypeBasic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVariableTypeBasic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVariableTypeBasic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVariableTypeBasic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTypeBasicContext variableTypeBasic() throws RecognitionException {
		VariableTypeBasicContext _localctx = new VariableTypeBasicContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableTypeBasic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(T__2);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
				{
				{
				setState(140);
				statement();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class JumpStmtContext extends StatementContext {
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public JumpStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterJumpStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitJumpStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitJumpStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStmtContext extends StatementContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoopStmtContext extends StatementContext {
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public LoopStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLoopStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLoopStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLoopStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableStmtContext extends StatementContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VariableStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVariableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVariableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVariableStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBlockStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBlockStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionStmtContext extends StatementContext {
		public ConditionStatementContext conditionStatement() {
			return getRuleContext(ConditionStatementContext.class,0);
		}
		public ConditionStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConditionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConditionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConditionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statement);
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				block();
				}
				break;
			case 2:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				expr(0);
				setState(150);
				match(T__4);
				}
				break;
			case 3:
				_localctx = new ConditionStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				conditionStatement();
				}
				break;
			case 4:
				_localctx = new LoopStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				loopStatement();
				}
				break;
			case 5:
				_localctx = new JumpStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(154);
				jumpStatement();
				}
				break;
			case 6:
				_localctx = new VariableStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(155);
				varDecl();
				}
				break;
			case 7:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionStatementContext extends ParserRuleContext {
		public StatementContext thenStatement;
		public StatementContext elseStatement;
		public TerminalNode IF() { return getToken(MxstarParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxstarParser.ELSE, 0); }
		public ConditionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConditionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConditionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConditionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionStatementContext conditionStatement() throws RecognitionException {
		ConditionStatementContext _localctx = new ConditionStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conditionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(IF);
			setState(160);
			match(T__0);
			setState(161);
			expr(0);
			setState(162);
			match(T__1);
			setState(163);
			((ConditionStatementContext)_localctx).thenStatement = statement();
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(164);
				match(ELSE);
				setState(165);
				((ConditionStatementContext)_localctx).elseStatement = statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(MxstarParser.CONTINUE, 0); }
		public TerminalNode BREAK() { return getToken(MxstarParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(MxstarParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_jumpStatement);
		int _la;
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONTINUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(CONTINUE);
				setState(169);
				match(T__4);
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(BREAK);
				setState(171);
				match(T__4);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				match(RETURN);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
					{
					setState(173);
					expr(0);
					}
				}

				setState(176);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	 
		public LoopStatementContext() { }
		public void copyFrom(LoopStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WhileExprContext extends LoopStatementContext {
		public TerminalNode WHILE() { return getToken(MxstarParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileExprContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterWhileExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitWhileExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitWhileExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForExprContext extends LoopStatementContext {
		public ExprContext initialize;
		public ExprContext condition;
		public ExprContext step;
		public TerminalNode FOR() { return getToken(MxstarParser.FOR, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ForExprContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterForExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitForExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_loopStatement);
		int _la;
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				_localctx = new WhileExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(WHILE);
				setState(180);
				match(T__0);
				setState(181);
				expr(0);
				setState(182);
				match(T__1);
				setState(183);
				statement();
				}
				break;
			case FOR:
				_localctx = new ForExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(FOR);
				setState(186);
				match(T__0);
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
					{
					setState(187);
					((ForExprContext)_localctx).initialize = expr(0);
					}
				}

				setState(190);
				match(T__4);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
					{
					setState(191);
					((ForExprContext)_localctx).condition = expr(0);
					}
				}

				setState(194);
				match(T__4);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
					{
					setState(195);
					((ForExprContext)_localctx).step = expr(0);
					}
				}

				setState(198);
				match(T__1);
				setState(199);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(MxstarParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExprContext {
		public Token prefix;
		public Token postfix;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryExprContext extends ExprContext {
		public Token token;
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode THIS() { return getToken(MxstarParser.THIS, 0); }
		public TerminalNode Bool_Literal() { return getToken(MxstarParser.Bool_Literal, 0); }
		public TerminalNode String_Literal() { return getToken(MxstarParser.String_Literal, 0); }
		public TerminalNode Integer_Literal() { return getToken(MxstarParser.Integer_Literal, 0); }
		public TerminalNode NULL_Literal() { return getToken(MxstarParser.NULL_Literal, 0); }
		public PrimaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public MemberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExprContext {
		public Token bop;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExprContext {
		public Token bop;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuntionExprContext extends ExprContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FuntionExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuntionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuntionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuntionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(203);
				match(Identifier);
				}
				break;
			case 2:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(204);
				match(THIS);
				}
				break;
			case 3:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205);
				((PrimaryExprContext)_localctx).token = match(Bool_Literal);
				}
				break;
			case 4:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(206);
				((PrimaryExprContext)_localctx).token = match(String_Literal);
				}
				break;
			case 5:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
				((PrimaryExprContext)_localctx).token = match(Integer_Literal);
				}
				break;
			case 6:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(208);
				((PrimaryExprContext)_localctx).token = match(NULL_Literal);
				}
				break;
			case 7:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(209);
				match(NEW);
				setState(210);
				creator();
				}
				break;
			case 8:
				{
				_localctx = new SubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211);
				match(T__0);
				setState(212);
				expr(0);
				setState(213);
				match(T__1);
				}
				break;
			case 9:
				{
				_localctx = new FuntionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215);
				functionCall();
				}
				break;
			case 10:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(216);
				((UnaryExprContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
					((UnaryExprContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(217);
				expr(14);
				}
				break;
			case 11:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(218);
				((UnaryExprContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
					((UnaryExprContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(219);
				expr(13);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(276);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(274);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(222);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(223);
						((BinaryExprContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((BinaryExprContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(224);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(225);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(226);
						((BinaryExprContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((BinaryExprContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(227);
						expr(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(229);
						((BinaryExprContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((BinaryExprContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(230);
						expr(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(231);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(232);
						((BinaryExprContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
							((BinaryExprContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(233);
						expr(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(235);
						((BinaryExprContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__26) ) {
							((BinaryExprContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(236);
						expr(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(237);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(238);
						((BinaryExprContext)_localctx).bop = match(T__27);
						setState(239);
						expr(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(240);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(241);
						((BinaryExprContext)_localctx).bop = match(T__28);
						setState(242);
						expr(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(244);
						((BinaryExprContext)_localctx).bop = match(T__29);
						setState(245);
						expr(6);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(247);
						((BinaryExprContext)_localctx).bop = match(T__30);
						setState(248);
						expr(5);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(250);
						((BinaryExprContext)_localctx).bop = match(T__31);
						setState(251);
						expr(4);
						}
						break;
					case 11:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(252);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(253);
						((BinaryExprContext)_localctx).bop = match(T__32);
						setState(254);
						expr(0);
						setState(255);
						match(T__33);
						setState(256);
						expr(3);
						}
						break;
					case 12:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(259);
						((AssignExprContext)_localctx).bop = match(T__6);
						setState(260);
						expr(1);
						}
						break;
					case 13:
						{
						_localctx = new MemberExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(261);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(262);
						match(T__9);
						setState(265);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(263);
							match(Identifier);
							}
							break;
						case 2:
							{
							setState(264);
							functionCall();
							}
							break;
						}
						}
						break;
					case 14:
						{
						_localctx = new ArrayExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(267);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(268);
						match(T__7);
						setState(269);
						expr(0);
						setState(270);
						match(T__8);
						}
						break;
					case 15:
						{
						_localctx = new UnaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(273);
						((UnaryExprContext)_localctx).postfix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__10 || _la==T__11) ) {
							((UnaryExprContext)_localctx).postfix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(278);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
	 
		public CreatorContext() { }
		public void copyFrom(CreatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InvalidCreaterContext extends CreatorContext {
		public VariableTypeBasicContext variableTypeBasic() {
			return getRuleContext(VariableTypeBasicContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EmptyContext> empty() {
			return getRuleContexts(EmptyContext.class);
		}
		public EmptyContext empty(int i) {
			return getRuleContext(EmptyContext.class,i);
		}
		public InvalidCreaterContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterInvalidCreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitInvalidCreater(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitInvalidCreater(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValidCreaterContext extends CreatorContext {
		public VariableTypeBasicContext variableTypeBasic() {
			return getRuleContext(VariableTypeBasicContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EmptyContext> empty() {
			return getRuleContexts(EmptyContext.class);
		}
		public EmptyContext empty(int i) {
			return getRuleContext(EmptyContext.class,i);
		}
		public ValidCreaterContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterValidCreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitValidCreater(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitValidCreater(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_creator);
		try {
			int _alt;
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new InvalidCreaterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				variableTypeBasic();
				{
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(280);
						match(T__7);
						setState(281);
						expr(0);
						setState(282);
						match(T__8);
						}
						} 
					}
					setState(288);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				}
				setState(293); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(289);
						match(T__7);
						setState(290);
						empty();
						setState(291);
						match(T__8);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(295); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(301); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(297);
						match(T__7);
						setState(298);
						expr(0);
						setState(299);
						match(T__8);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(303); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				}
				break;
			case 2:
				_localctx = new ValidCreaterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				variableTypeBasic();
				setState(326);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(312);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(306);
							match(T__7);
							setState(307);
							expr(0);
							setState(308);
							match(T__8);
							}
							} 
						}
						setState(314);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
					}
					setState(321);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(315);
							match(T__7);
							setState(316);
							empty();
							setState(317);
							match(T__8);
							}
							} 
						}
						setState(323);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
					}
					}
					break;
				case 2:
					{
					{
					setState(324);
					match(T__0);
					setState(325);
					match(T__1);
					}
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(Identifier);
			setState(331);
			match(T__0);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << Integer_Literal) | (1L << NULL_Literal) | (1L << String_Literal) | (1L << Bool_Literal) | (1L << Identifier))) != 0)) {
				{
				setState(332);
				expr(0);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(333);
					match(T__5);
					setState(334);
					expr(0);
					}
					}
					setState(339);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(342);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 20);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u015b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\5\3<\n\3\3\4\3\4\3\4\3\4\5\4"+
		"B\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5K\n\5\f\5\16\5N\13\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7Z\n\7\f\7\16\7]\13\7\3\b\3\b\3\b\5\bb"+
		"\n\b\3\t\3\t\3\t\5\tg\n\t\3\n\3\n\3\n\5\nl\n\n\3\n\3\n\3\n\3\13\3\13\5"+
		"\13s\n\13\3\f\3\f\3\f\7\fx\n\f\f\f\16\f{\13\f\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\7\16\u0085\n\16\f\16\16\16\u0088\13\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\7\21\u0090\n\21\f\21\16\21\u0093\13\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00a0\n\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u00a9\n\23\3\24\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u00b1\n\24\3\24\5\24\u00b4\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\5\25\u00bf\n\25\3\25\3\25\5\25\u00c3\n\25\3\25\3\25\5\25\u00c7"+
		"\n\25\3\25\3\25\5\25\u00cb\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00df\n\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u010c\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0115\n\26\f"+
		"\26\16\26\u0118\13\26\3\27\3\27\3\27\3\27\3\27\7\27\u011f\n\27\f\27\16"+
		"\27\u0122\13\27\3\27\3\27\3\27\3\27\6\27\u0128\n\27\r\27\16\27\u0129\3"+
		"\27\3\27\3\27\3\27\6\27\u0130\n\27\r\27\16\27\u0131\3\27\3\27\3\27\3\27"+
		"\3\27\7\27\u0139\n\27\f\27\16\27\u013c\13\27\3\27\3\27\3\27\3\27\7\27"+
		"\u0142\n\27\f\27\16\27\u0145\13\27\3\27\3\27\5\27\u0149\n\27\5\27\u014b"+
		"\n\27\3\30\3\30\3\30\3\30\3\30\7\30\u0152\n\30\f\30\16\30\u0155\13\30"+
		"\5\30\u0157\n\30\3\30\3\30\3\30\2\3*\31\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\2\13\4\2%\'\67\67\3\2\r\20\3\2\21\22\3\2\23\25\3\2"+
		"\17\20\3\2\26\27\3\2\30\33\3\2\34\35\3\2\r\16\2\u0182\2\63\3\2\2\2\4;"+
		"\3\2\2\2\6=\3\2\2\2\bF\3\2\2\2\nR\3\2\2\2\fV\3\2\2\2\16^\3\2\2\2\20f\3"+
		"\2\2\2\22h\3\2\2\2\24r\3\2\2\2\26t\3\2\2\2\30|\3\2\2\2\32\177\3\2\2\2"+
		"\34\u0089\3\2\2\2\36\u008b\3\2\2\2 \u008d\3\2\2\2\"\u009f\3\2\2\2$\u00a1"+
		"\3\2\2\2&\u00b3\3\2\2\2(\u00ca\3\2\2\2*\u00de\3\2\2\2,\u014a\3\2\2\2."+
		"\u014c\3\2\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2"+
		"\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\2\2\3\67\3\3\2\2\2"+
		"8<\5\6\4\29<\5\b\5\2:<\5\n\6\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\5\3\2\2"+
		"\2=>\5\24\13\2>?\7\67\2\2?A\7\3\2\2@B\5\26\f\2A@\3\2\2\2AB\3\2\2\2BC\3"+
		"\2\2\2CD\7\4\2\2DE\5 \21\2E\7\3\2\2\2FG\7\61\2\2GH\7\67\2\2HL\7\5\2\2"+
		"IK\5\20\t\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2"+
		"\2OP\7\6\2\2PQ\7\7\2\2Q\t\3\2\2\2RS\5\32\16\2ST\5\f\7\2TU\7\7\2\2U\13"+
		"\3\2\2\2V[\5\16\b\2WX\7\b\2\2XZ\5\16\b\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2"+
		"[\\\3\2\2\2\\\r\3\2\2\2][\3\2\2\2^a\7\67\2\2_`\7\t\2\2`b\5*\26\2a_\3\2"+
		"\2\2ab\3\2\2\2b\17\3\2\2\2cg\5\6\4\2dg\5\n\6\2eg\5\22\n\2fc\3\2\2\2fd"+
		"\3\2\2\2fe\3\2\2\2g\21\3\2\2\2hi\7\67\2\2ik\7\3\2\2jl\5\26\f\2kj\3\2\2"+
		"\2kl\3\2\2\2lm\3\2\2\2mn\7\4\2\2no\5 \21\2o\23\3\2\2\2ps\5\32\16\2qs\7"+
		"(\2\2rp\3\2\2\2rq\3\2\2\2s\25\3\2\2\2ty\5\30\r\2uv\7\b\2\2vx\5\30\r\2"+
		"wu\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\27\3\2\2\2{y\3\2\2\2|}\5\32"+
		"\16\2}~\7\67\2\2~\31\3\2\2\2\177\u0086\5\36\20\2\u0080\u0081\7\n\2\2\u0081"+
		"\u0082\5\34\17\2\u0082\u0083\7\13\2\2\u0083\u0085\3\2\2\2\u0084\u0080"+
		"\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\33\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\3\2\2\2\u008a\35\3\2\2\2\u008b"+
		"\u008c\t\2\2\2\u008c\37\3\2\2\2\u008d\u0091\7\5\2\2\u008e\u0090\5\"\22"+
		"\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\6\2\2\u0095"+
		"!\3\2\2\2\u0096\u00a0\5 \21\2\u0097\u0098\5*\26\2\u0098\u0099\7\7\2\2"+
		"\u0099\u00a0\3\2\2\2\u009a\u00a0\5$\23\2\u009b\u00a0\5(\25\2\u009c\u00a0"+
		"\5&\24\2\u009d\u00a0\5\n\6\2\u009e\u00a0\7\7\2\2\u009f\u0096\3\2\2\2\u009f"+
		"\u0097\3\2\2\2\u009f\u009a\3\2\2\2\u009f\u009b\3\2\2\2\u009f\u009c\3\2"+
		"\2\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0#\3\2\2\2\u00a1\u00a2"+
		"\7)\2\2\u00a2\u00a3\7\3\2\2\u00a3\u00a4\5*\26\2\u00a4\u00a5\7\4\2\2\u00a5"+
		"\u00a8\5\"\22\2\u00a6\u00a7\7*\2\2\u00a7\u00a9\5\"\22\2\u00a8\u00a6\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9%\3\2\2\2\u00aa\u00ab\7.\2\2\u00ab\u00b4"+
		"\7\7\2\2\u00ac\u00ad\7-\2\2\u00ad\u00b4\7\7\2\2\u00ae\u00b0\7/\2\2\u00af"+
		"\u00b1\5*\26\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b4\7\7\2\2\u00b3\u00aa\3\2\2\2\u00b3\u00ac\3\2\2\2\u00b3"+
		"\u00ae\3\2\2\2\u00b4\'\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6\u00b7\7\3\2\2"+
		"\u00b7\u00b8\5*\26\2\u00b8\u00b9\7\4\2\2\u00b9\u00ba\5\"\22\2\u00ba\u00cb"+
		"\3\2\2\2\u00bb\u00bc\7+\2\2\u00bc\u00be\7\3\2\2\u00bd\u00bf\5*\26\2\u00be"+
		"\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\7\7"+
		"\2\2\u00c1\u00c3\5*\26\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c6\7\7\2\2\u00c5\u00c7\5*\26\2\u00c6\u00c5\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\7\4\2\2\u00c9"+
		"\u00cb\5\"\22\2\u00ca\u00b5\3\2\2\2\u00ca\u00bb\3\2\2\2\u00cb)\3\2\2\2"+
		"\u00cc\u00cd\b\26\1\2\u00cd\u00df\7\67\2\2\u00ce\u00df\7\62\2\2\u00cf"+
		"\u00df\7\66\2\2\u00d0\u00df\7\65\2\2\u00d1\u00df\7\63\2\2\u00d2\u00df"+
		"\7\64\2\2\u00d3\u00d4\7\60\2\2\u00d4\u00df\5,\27\2\u00d5\u00d6\7\3\2\2"+
		"\u00d6\u00d7\5*\26\2\u00d7\u00d8\7\4\2\2\u00d8\u00df\3\2\2\2\u00d9\u00df"+
		"\5.\30\2\u00da\u00db\t\3\2\2\u00db\u00df\5*\26\20\u00dc\u00dd\t\4\2\2"+
		"\u00dd\u00df\5*\26\17\u00de\u00cc\3\2\2\2\u00de\u00ce\3\2\2\2\u00de\u00cf"+
		"\3\2\2\2\u00de\u00d0\3\2\2\2\u00de\u00d1\3\2\2\2\u00de\u00d2\3\2\2\2\u00de"+
		"\u00d3\3\2\2\2\u00de\u00d5\3\2\2\2\u00de\u00d9\3\2\2\2\u00de\u00da\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00df\u0116\3\2\2\2\u00e0\u00e1\f\16\2\2\u00e1"+
		"\u00e2\t\5\2\2\u00e2\u0115\5*\26\17\u00e3\u00e4\f\r\2\2\u00e4\u00e5\t"+
		"\6\2\2\u00e5\u0115\5*\26\16\u00e6\u00e7\f\f\2\2\u00e7\u00e8\t\7\2\2\u00e8"+
		"\u0115\5*\26\r\u00e9\u00ea\f\13\2\2\u00ea\u00eb\t\b\2\2\u00eb\u0115\5"+
		"*\26\f\u00ec\u00ed\f\n\2\2\u00ed\u00ee\t\t\2\2\u00ee\u0115\5*\26\13\u00ef"+
		"\u00f0\f\t\2\2\u00f0\u00f1\7\36\2\2\u00f1\u0115\5*\26\n\u00f2\u00f3\f"+
		"\b\2\2\u00f3\u00f4\7\37\2\2\u00f4\u0115\5*\26\t\u00f5\u00f6\f\7\2\2\u00f6"+
		"\u00f7\7 \2\2\u00f7\u0115\5*\26\b\u00f8\u00f9\f\6\2\2\u00f9\u00fa\7!\2"+
		"\2\u00fa\u0115\5*\26\7\u00fb\u00fc\f\5\2\2\u00fc\u00fd\7\"\2\2\u00fd\u0115"+
		"\5*\26\6\u00fe\u00ff\f\4\2\2\u00ff\u0100\7#\2\2\u0100\u0101\5*\26\2\u0101"+
		"\u0102\7$\2\2\u0102\u0103\5*\26\5\u0103\u0115\3\2\2\2\u0104\u0105\f\3"+
		"\2\2\u0105\u0106\7\t\2\2\u0106\u0115\5*\26\3\u0107\u0108\f\26\2\2\u0108"+
		"\u010b\7\f\2\2\u0109\u010c\7\67\2\2\u010a\u010c\5.\30\2\u010b\u0109\3"+
		"\2\2\2\u010b\u010a\3\2\2\2\u010c\u0115\3\2\2\2\u010d\u010e\f\24\2\2\u010e"+
		"\u010f\7\n\2\2\u010f\u0110\5*\26\2\u0110\u0111\7\13\2\2\u0111\u0115\3"+
		"\2\2\2\u0112\u0113\f\21\2\2\u0113\u0115\t\n\2\2\u0114\u00e0\3\2\2\2\u0114"+
		"\u00e3\3\2\2\2\u0114\u00e6\3\2\2\2\u0114\u00e9\3\2\2\2\u0114\u00ec\3\2"+
		"\2\2\u0114\u00ef\3\2\2\2\u0114\u00f2\3\2\2\2\u0114\u00f5\3\2\2\2\u0114"+
		"\u00f8\3\2\2\2\u0114\u00fb\3\2\2\2\u0114\u00fe\3\2\2\2\u0114\u0104\3\2"+
		"\2\2\u0114\u0107\3\2\2\2\u0114\u010d\3\2\2\2\u0114\u0112\3\2\2\2\u0115"+
		"\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117+\3\2\2\2"+
		"\u0118\u0116\3\2\2\2\u0119\u0120\5\36\20\2\u011a\u011b\7\n\2\2\u011b\u011c"+
		"\5*\26\2\u011c\u011d\7\13\2\2\u011d\u011f\3\2\2\2\u011e\u011a\3\2\2\2"+
		"\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0127"+
		"\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7\n\2\2\u0124\u0125\5\34\17\2"+
		"\u0125\u0126\7\13\2\2\u0126\u0128\3\2\2\2\u0127\u0123\3\2\2\2\u0128\u0129"+
		"\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012f\3\2\2\2\u012b"+
		"\u012c\7\n\2\2\u012c\u012d\5*\26\2\u012d\u012e\7\13\2\2\u012e\u0130\3"+
		"\2\2\2\u012f\u012b\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u014b\3\2\2\2\u0133\u0148\5\36\20\2\u0134\u0135\7"+
		"\n\2\2\u0135\u0136\5*\26\2\u0136\u0137\7\13\2\2\u0137\u0139\3\2\2\2\u0138"+
		"\u0134\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2"+
		"\2\2\u013b\u0143\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7\n\2\2\u013e"+
		"\u013f\5\34\17\2\u013f\u0140\7\13\2\2\u0140\u0142\3\2\2\2\u0141\u013d"+
		"\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		"\u0149\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0147\7\3\2\2\u0147\u0149\7\4"+
		"\2\2\u0148\u013a\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014b\3\2\2\2\u014a"+
		"\u0119\3\2\2\2\u014a\u0133\3\2\2\2\u014b-\3\2\2\2\u014c\u014d\7\67\2\2"+
		"\u014d\u0156\7\3\2\2\u014e\u0153\5*\26\2\u014f\u0150\7\b\2\2\u0150\u0152"+
		"\5*\26\2\u0151\u014f\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u014e\3\2"+
		"\2\2\u0156\u0157\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\7\4\2\2\u0159"+
		"/\3\2\2\2#\63;AL[afkry\u0086\u0091\u009f\u00a8\u00b0\u00b3\u00be\u00c2"+
		"\u00c6\u00ca\u00de\u010b\u0114\u0116\u0120\u0129\u0131\u013a\u0143\u0148"+
		"\u014a\u0153\u0156";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}