package Mxstar.ErrorProcessor;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import Mxstar.FrontEnd.*;

public class ParserErrorListener extends BaseErrorListener {
    private CompileErrorListener compileErrorListener;
    public ParserErrorListener(CompileErrorListener compileErrorListener) {
        this.compileErrorListener = compileErrorListener;
    }
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        compileErrorListener.addError(new Position(line, charPositionInLine),msg);
    }
    public boolean hasError() {
        return compileErrorListener.hasError();
    }
}