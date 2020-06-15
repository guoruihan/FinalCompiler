package Mxstar.IR;

import Mxstar.IR.Inst.*;

import java.util.LinkedList;
import java.util.List;

public class BB {
    public String hint;
    public Func BelongFunc;
    public Inst head;
    public Inst tail;
    public int BID;
    public static int totBID = 0;


    public List<BB> frontiers;
    public List<BB> successers;

    public BB(Func belongFunc,String hint){
        this.head = null;
        this.tail = null;
        this.hint = hint;
        this.BelongFunc = belongFunc;
        this.BelongFunc.basicblocks.add(this);
        this.BID = totBID ++;
        this.frontiers = new LinkedList<>();
        this.successers = new LinkedList<>();
    }


    public void prepend(Inst inst)  {
        head.prepend(inst);
    }
    public boolean isEnded() {
        return tail instanceof Jump || tail instanceof Return || tail instanceof Cjump;
    }

    public void append(Inst inst) {
        if (isEnded())
            return ;
        if (head == null) {
            inst.prev = null;
            inst.next = null;
            head = tail = inst;
        } else {
            tail.append(inst);
        }
    }
    public void accept(IRVisitor visitor) {visitor.visit((this));}
}
