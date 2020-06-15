package Mxstar.IR.Operand;

import Mxstar.IR.IRVisitor;

public class VirtualReg extends Register {
    public String name;
    public int id;
    public static int totid;

    public PhysicalReg allocatedPhyReg;
    public AlloSpace spillPlace = null;
    public int cntUD;

    public VirtualReg(String name){
        this.name = name;
        this.id = totid ++;
        this.allocatedPhyReg = null;
    }

    public VirtualReg(String name,  PhysicalReg phyReg) {
        this.name = name;
        this.allocatedPhyReg = phyReg;
    }

    public  void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
