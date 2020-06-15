package Mxstar.Symbol;


import Mxstar.Configuration;

import java.util.Collection;

public class ClassType extends VarType{
    public String name;

    public ClassSymbol symbol;

    public ClassType() {
        name = null;
        symbol = null;
    }
    public ClassType(String name, ClassSymbol symbol)  {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public boolean match(VarType other) {
        if(other instanceof  ClassType) {
            String otherName = ((ClassType) other).name;
            if((otherName.equals("null") && name.equals("string")) || (name.equals("null") && otherName.equals("string")))
                return false;
            else
                return otherName.equals("null") || name.equals("null") || otherName.equals(name);
        } else {
            return false;
        }
    }//I don't understand

    @Override
    public int getBytes() {
        return symbol.symbolTable.variables.size() * Configuration.REGISTER_WIDTH;
    }
}