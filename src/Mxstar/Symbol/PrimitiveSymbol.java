package Mxstar.Symbol;



import Mxstar.FrontEnd.*;

public class PrimitiveSymbol extends TypeSymbol{
    public String name;
    public Position position;

    public PrimitiveSymbol() {
        this.name = null;
        this.position = new Position(0, 0);
    }
    public PrimitiveSymbol(String name, Position position) {
        this.name = name;
        this.position = position;
    }
    public PrimitiveSymbol(String name) {
        this.name = name;
        this.position = new Position(0, 0);
/*
import Mxstar.ErrorProcessor.*;

public class PrimitiveSymbol extends TypeSymbol{
    public String name;
    public Location location;

    public PrimitiveSymbol() {
        this.name = null;
        this.location = new Location(0, 0);
    }
    public PrimitiveSymbol(String name, Location location) {
        this.name = name;
        this.location = location;
    }
    public PrimitiveSymbol(String name) {
        this.name = name;
        this.location = new Location(0, 0);
*/
    }
}