package Mxstar.ErrorProcessor;
import Mxstar.FrontEnd.*;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CompileErrorListener extends RuntimeException {
    private List<String> errorList;

    public CompileErrorListener() {
        errorList = new LinkedList<>();
    }

    public void addError(Position loc, String msg) {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        errorList.add(stackTraceElements[1].getClassName() + '.' + stackTraceElements[1].getLineNumber() + ':' + loc +  ':' + msg);
    }

    public boolean hasError() {
        return (!errorList.isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("There are in total " + errorList.size() + " errors:\n");
        for(String s : errorList) {
            stringBuilder.append(s + '\n');
        }
        return stringBuilder.toString();
    }

    public void printTo(PrintStream out) {
        out.print(toString());
    }
}