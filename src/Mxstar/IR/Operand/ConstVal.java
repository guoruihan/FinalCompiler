package Mxstar.IR.Operand;

import Mxstar.Configuration;
import Mxstar.IR.IRVisitor;

public class ConstVal extends Constant{

    public String hint;
    public int bytes;
    public String init;
    public int tag = 0;
    public ConstVal(String hint, int bytes) {//名字和大小
        this.hint = hint;
        this.bytes = bytes;
        this.init = null;
    }
    public ConstVal(String hint, int bytes,int tag) {//名字和大小
        this.hint = hint;
        this.bytes = bytes;
        this.init = null;
        this.tag = tag;
    }
    public ConstVal(String hint, String init) {//字符串常量
        this.hint = hint;
        this.init = init;
        this.bytes = init.length() + 1 + Configuration.REGISTER_WIDTH;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

