package Mxstar.Symbol;



import Mxstar.ErrorProcessor.*;
import Mxstar.FrontEnd.Position;
/*
//1 import Mxstar.IR.Func;
import Mxstar.ErrorProcessor.*;
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FuncSymbol extends TypeSymbol{
    public String name;

    public Position position;
/*
    public Location location;
*/
    public VarType returnType;
    public List<VarType> parameterTypes;
    public List<String> parameterNames;
    public SymbolTable funtionSymbolTable;



    public HashSet<VarSymbol> usedGlobalVariables;
    public boolean Global;

    public HashSet<FuncSymbol> calleeSet;
    public HashSet<FuncSymbol> callerSet;

/*
    public HashSet<VarSymbol> usedGlobalVariables;
    public boolean Global;

*/
    public HashSet<FuncSymbol> visited;

    public FuncSymbol() {
        this.parameterNames = new LinkedList<>();
        this.parameterTypes = new LinkedList<>();
        this.usedGlobalVariables = new HashSet<>();

        this.calleeSet = new HashSet<>();
        this.callerSet = new HashSet<>();
        this.visited = new HashSet<>();
    }
    public FuncSymbol(String name, VarType returnType, Position position, VarType paraType, String paraname, boolean global){
        this.name = name;
        this.position = position;
        this.returnType = returnType;
        this.parameterNames = new LinkedList<>();
        this.parameterTypes = new LinkedList<>();
        parameterTypes.add(paraType);
        parameterNames.add(paraname);
        Global = global;
    }
    public FuncSymbol(String name, VarType returnType, Position position, boolean global){
        this.name = name;
        this.position = position;
        this.returnType = returnType;
        this.parameterNames = new LinkedList<>();
        this.parameterTypes = new LinkedList<>();
        Global = global;
    }
    public FuncSymbol(Position nposition, String nname, VarType nreturnType) {
        name = nname;
        position = nposition;
/*
        this.visited = new HashSet<>();
    }
    public FuncSymbol(Location nlocation,String nname,VarType nreturnType) {
        name = nname;
        location = nlocation;
*/
        returnType = nreturnType;
        this.parameterNames = new LinkedList<>();
        this.parameterTypes = new LinkedList<>();
        this.usedGlobalVariables = new HashSet<>();
        this.visited = new HashSet<>();
    }



}