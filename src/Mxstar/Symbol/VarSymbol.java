package Mxstar.Symbol;




import Mxstar.FrontEnd.*;
import Mxstar.IR.Operand.*;
/*
//import Mxstar.IR.Operand.VirReg;

*/

public class VarSymbol extends TypeSymbol{
    public String name;
    public VarType variableType;

    public Position position;

    public boolean isClassField;//what's this
    public boolean isGlobalVariable;//what's this
    public VirtualReg VirtualReg;
/*
    public Location location;

    public boolean isClassField;//what's this
    public boolean isGlobalVariable;//what's this
//  public VirReg virReg;
*/


    public VarSymbol() {
        name = null;
        variableType = null;

        position = null;
    }
    public VarSymbol(String name, VarType variableType, Position position, boolean isClassField, boolean isGlobalVariable) {

        this.name = name;
        this.variableType = variableType;
        this.position = position;
/*
        location = null;
    }
    public VarSymbol(String name, VarType variableType, Location location, boolean isClassField, boolean isGlobalVariable) {
 //2 public VarSymbol(String name, VarType variableType, Location location) {
        this.name = name;
        this.variableType = variableType;
        this.location = location;
*/
        this.isClassField = isClassField;
        this.isGlobalVariable = isGlobalVariable;
    }

}