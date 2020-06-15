package Mxstar.Symbol;

import Mxstar.Configuration;

public class ArrayType extends VarType {
    public VarType baseType;

    public ArrayType () {
        baseType = null;
    }

    public  ArrayType (VarType baseType) {
        this.baseType = baseType;
    }

    public boolean match(VarType other) {
        if(other instanceof  ClassType && ((ClassType)other).name.equals("null"))
            return true;
        else if(other instanceof  ArrayType) {
            return baseType.match(((ArrayType) other).baseType);
        } else
            return false;
    }
    public int getBytes() {
        return Configuration.REGISTER_WIDTH;
    }
}