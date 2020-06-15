package Mxstar.Symbol;


public abstract class VarType {
    public abstract boolean match(VarType other);
    public abstract int getBytes();
}