package Mxstar.Symbol;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Mxstar.Configuration;
import org.antlr.v4.misc.OrderedHashMap;
/*
* 血泪教训：没有类中类！
*
* */
public class SymbolTable {
    public Map<String, FuncSymbol> functions;
    public Map<String, VarSymbol> variables;
    public SymbolTable parent;

    public Map<String, Integer> imm;
    public Integer curImm;
/*
    public List<SymbolTable> children;
    public Map<String, Integer> offset;
    public Integer curOffset;


    public SymbolTable() {
        contents = new LinkedHashMap<>();
        //variables = new LinkedHashMap<>();
        parent = new SymbolTable();
        children = new LinkedList<>();
    }
*/

    public SymbolTable(SymbolTable symbolTable) {
        functions = new LinkedHashMap<>();
        variables = new LinkedHashMap<>();
        parent = symbolTable;

        curImm = 0;
        imm = new OrderedHashMap<>();
    }

    public int getOffset(String name) {
        return imm.get(name);
    }
    public FuncSymbol getFS(String name) {
        return functions.get(name);
    }
    public VarSymbol getVS(String name) {
        return variables.get(name);
    }
    public void putVar(String name, VarSymbol variableSymbol) {
        variables.put(name, variableSymbol);
        imm.put(name, curImm);
        curImm += Configuration.REGISTER_WIDTH;
    }
    public void putFunc(String name, FuncSymbol functionSymbol) {
/*
        children = new LinkedList<>();
        curOffset = 0;
        offset = new OrderedHashMap<>();
    }

    /*1 public int getVariableOffset(String name) {
        return offset.get(name);
    }
    public FuncSymbol getFunctionSymbol(String name) {
        return functions.get(name);
    }
    public VarSymbol getVariableSymbol(String name) {
        return variables.get(name);
    }
    public void putVariableSymbol(String name, VarSymbol variableSymbol) {
        variables.put(name, variableSymbol);
        offset.put(name, curOffset);
        curOffset += Configuration.REGISTER_WIDTH;
    }
    public void putFunctionSymbol(String name, FuncSymbol functionSymbol) {
*/
        functions.put(name, functionSymbol);
    }

}