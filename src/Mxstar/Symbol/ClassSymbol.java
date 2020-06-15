package Mxstar.Symbol;

import Mxstar.ErrorProcessor.*;

import Mxstar.FrontEnd.*;

public class ClassSymbol extends TypeSymbol{
    public String name;
    public Position position;
    public SymbolTable symbolTable;
    ClassSymbol(){
    }
    public ClassSymbol(String nname,Position nposition,SymbolTable nsymbolTable){
        name = nname;
        position = nposition;
/*

public class ClassSymbol extends TypeSymbol{
    public String name;
    public Location location;
    public SymbolTable symbolTable;
    ClassSymbol(){
    }
    public ClassSymbol(String nname,Location nlocation,SymbolTable nsymbolTable){
        name = nname;
        location = nlocation;
*/
        symbolTable = nsymbolTable;
    }
}