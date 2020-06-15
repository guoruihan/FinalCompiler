package Mxstar.Symbol;


import Mxstar.FrontEnd.*;
/*
import Mxstar.ErrorProcessor.*;
*/

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalSymbolTable extends SymbolTable{
    public Map<String, ClassSymbol> classes;
    public Map<String, PrimitiveSymbol> primitives;
    public HashSet<VarSymbol> globalInitVars;

    public GlobalSymbolTable() {
        super(null);//子类调用父类的构造函数
        classes = new LinkedHashMap<>();
        primitives = new LinkedHashMap<>();
        globalInitVars = new HashSet<>();
        addDefaults();
    }

    public void putClassSymbol(String name, ClassSymbol classSymbol) {
        classes.put(name, classSymbol);
    }

/*
    public void putPrimitiveSymbol(String name, PrimitiveSymbol primitiveSymbol) {
        primitives.put(name, primitiveSymbol);
    }
*/
    public ClassSymbol getClassSymbol(String name) {
        return classes.get(name);
    }
    public PrimitiveSymbol getPrimitiveSymbol(String name) {
        return primitives.get(name);
    }

    private void addDefaultPrimitives() {
        primitives.put("int", new PrimitiveSymbol("int"));
        primitives.put("void", new PrimitiveSymbol("void"));
        primitives.put("bool", new PrimitiveSymbol("bool"));
    }

    private VarType voidType() {
        return new PrimitiveType("void", primitives.get("void"));
    }
    private VarType intType() {
        return new PrimitiveType("int", primitives.get("int"));
    }

    private VarType stringType() { return new ClassType("string", classes.get("string")); }

    private FuncSymbol stringLength() {
        FuncSymbol f = new FuncSymbol("string.length",intType(),new Position(0, 0),stringType(),"this",true);
/*
    private VarType stringType() {
        if (classes.get("string") == null) {
//            System.out.println("does not have string type");
        }
        return new ClassType("string", classes.get("string"));
    }

    private FuncSymbol stringLength() {
        FuncSymbol f = new FuncSymbol();
        f.name = "string.length";
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.location = new Location(0, 0);
        f.returnType = intType();
        f.Global = true;
*/
        return f;
    }

    private  FuncSymbol stringSubString() {

        FuncSymbol f = new FuncSymbol("string.substring",stringType(),new Position(0, 0),stringType(),"this",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "string.substring";
        f.location = new Location(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.returnType = stringType();
*/
        f.parameterNames.add("left");
        f.parameterTypes.add(intType());
        f.parameterNames.add("right");
        f.parameterTypes.add(intType());

/*
        f.Global = true;
*/
        return f;
    }

    private  FuncSymbol stringParseInt() {

        FuncSymbol f = new FuncSymbol("string.parseInt",intType(),new Position(0, 0),stringType(),"this",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "string.parseInt";
        f.location = new Location(0,0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.returnType = intType();
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol stringOrd() {

        FuncSymbol f = new FuncSymbol("string.ord",intType(),new Position(0, 0),stringType(),"this",true);
        f.parameterTypes.add(intType());
        f.parameterNames.add("pos");
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "string.ord";
        f.location = new Location(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.parameterTypes.add(intType());
        f.parameterNames.add("pos");
        f.returnType = intType();
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalPrint() {

        FuncSymbol f = new FuncSymbol("print",voidType(),new Position(0, 0),stringType(),"str",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "print";
        f.returnType = voidType();
        f.location = new Location(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("str");
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalPrintInt() {

        FuncSymbol f = new FuncSymbol("printInt",voidType(),new Position(0, 0),intType(),"int",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "printInt";
        f.returnType = voidType();
        f.location = new Location(0, 0);
        f.parameterTypes.add(intType());
        f.parameterNames.add("int");
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalPrintln() {

        FuncSymbol f = new FuncSymbol("println",voidType(),new Position(0, 0),stringType(),"str",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "println";
        f.returnType = voidType();
        f.location = new Location(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("str");
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalPrintlnInt() {

        FuncSymbol f = new FuncSymbol("printlnInt",voidType(),new Position(0, 0),intType(),"int",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "printlnInt";
        f.returnType = voidType();
        f.location = new Location(0, 0);
        f.parameterTypes.add(intType());
        f.parameterNames.add("int");
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalGetString() {

        FuncSymbol f = new FuncSymbol("getString",stringType(),new Position(0, 0),true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "getString";
        f.returnType = stringType();
        f.location = new Location(0, 0);
        f.Global = true;
*/
        return f;
    }

    private FuncSymbol globalGetInt() {

        FuncSymbol f = new FuncSymbol("getInt",intType(),new Position(0, 0),true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "getInt";
        f.returnType = intType();
        f.location = new Location(0, 0);
        f.Global = true;
*/
        return f;
    }

    private  FuncSymbol globalToString() {

        FuncSymbol f = new FuncSymbol("toString",stringType(),new Position(0, 0),intType(),"i",true);
/*
        FuncSymbol f = new FuncSymbol();
        f.name = "toString";
        f.returnType = stringType();
        f.location = new Location(0, 0);
        f.parameterTypes.add(intType());
        f.parameterNames.add("i");
        f.Global = true;
*/
        return f;
    }

    private void addDefualtString() {
        ClassSymbol stringSymbol = new ClassSymbol();
        stringSymbol.name = "string";

        stringSymbol.position = new Position(0, 0);
        stringSymbol.symbolTable = new SymbolTable(this);
        putClassSymbol("string", stringSymbol);
        stringSymbol.symbolTable.putFunc("length", stringLength());
        stringSymbol.symbolTable.putFunc("substring", stringSubString());
        stringSymbol.symbolTable.putFunc("parseInt", stringParseInt());
        stringSymbol.symbolTable.putFunc("ord", stringOrd());
/*
        stringSymbol.location = new Location(0, 0);
        stringSymbol.symbolTable = new SymbolTable(this);
        putClassSymbol("string", stringSymbol);
        stringSymbol.symbolTable.putFunctionSymbol("length", stringLength());
        stringSymbol.symbolTable.putFunctionSymbol("substring", stringSubString());
        stringSymbol.symbolTable.putFunctionSymbol("parseInt", stringParseInt());
        stringSymbol.symbolTable.putFunctionSymbol("ord", stringOrd());
*/
    }

    private void addDefaultNull() {
        ClassSymbol nullSymbol = new ClassSymbol();
        nullSymbol.name = "null";

        nullSymbol.position = new Position(0, 0);
/*
        nullSymbol.location = new Location(0, 0);
*/
        nullSymbol.symbolTable = new SymbolTable(this);
        putClassSymbol("null", nullSymbol);
    }

    private void addDefaultFunctions() {

        putFunc("print", globalPrint());
        putFunc("printInt", globalPrintInt());
        putFunc("println", globalPrintln());
        putFunc("printlnInt", globalPrintlnInt());
        putFunc("getString", globalGetString());
        putFunc("getInt", globalGetInt());
        putFunc("toString", globalToString());
/*
        putFunctionSymbol("print", globalPrint());
        putFunctionSymbol("printInt", globalPrintInt());
        putFunctionSymbol("println", globalPrintln());
        putFunctionSymbol("printlnInt", globalPrintlnInt());
        putFunctionSymbol("getString", globalGetString());
        putFunctionSymbol("getInt", globalGetInt());
        putFunctionSymbol("toString", globalToString());
*/
    }

    private void addDefaults() {
        addDefaultPrimitives();
        addDefualtString();
        addDefaultNull();
        addDefaultFunctions();
    }
    //默认函数留着以后一起处理
}