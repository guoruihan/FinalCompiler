package Mxstar.IR;

import Mxstar.Configuration;
import Mxstar.IR.BB;
import Mxstar.IR.Func;
import Mxstar.IR.IRProgram;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;
import Mxstar.IR.Regs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


/*
          +----------+
         sp-24 |    a     |
                +----------+
         sp-16 |    b     |
                +----------+
         sp-8  |    c     |
                +----------+
         sp    | Returnaddr  |
                +----------+
         sp+8  | caller's |
                | stack    |
                | frame    |
                | ...     |
                +----------+
 */

public class BuildStack {
    class Frame{
        public LinkedList<Stack> parameters = new LinkedList<>();
        public LinkedList<Stack> temporaries = new LinkedList<>();
        public int getFrameSize(int offset) {
//            System.out.println(parameters.size());
//            System.out.println(temporaries.size() + "\n");
            int bytes = Configuration.REGISTER_WIDTH * (parameters.size() + temporaries.size());
/*            bytes = (bytes + 15) / 16 * 16;
            if (offset % 2 == 1) {
                bytes += 8;
            }*///useless
            return bytes;
        }
    }

    public IRProgram irProgram;
    public HashMap<Func,  Frame> frameMap;

    public BuildStack(IRProgram irProgram) {
        this.irProgram = irProgram;
        frameMap = new HashMap<>();
    }

    public void processFunc(Func func) {
        Frame frame = new Frame();
        frameMap.put(func, frame);
        LinkedList<VirtualReg> parameters = func.parameters;
        for (int i = 6; i < parameters.size(); ++i) {
            Stack Stack = (Stack)parameters.get(i).spillPlace;
            frame.parameters.add(Stack);
        }

        HashSet<Stack> slots = new HashSet<>();
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                LinkedList<Stack> Stacks = new LinkedList<>(inst.getStackSlots());
                for (Stack Stack: Stacks) {
                    if (!frame.parameters.contains(Stack))
                        slots.add(Stack);
                }
//                slots.addAll(inst.getStacks());
//                System.out.println(slots.size());
            }

        }

        int nw = Configuration.REGISTER_WIDTH;

        frame.temporaries.addAll(slots);
        for (int i = 0; i < frame.parameters.size(); ++i) {
            Stack Stack = frame.parameters.get(i);
            Stack.base = Regs.s0;
            Stack.constant = new Imm(nw*2 + nw * i);
        }

        for (int i = 0; i < frame.temporaries.size(); ++i) {
            Stack Stack = frame.temporaries.get(i);
            Stack.base = Regs.s0;
            Stack.constant = new Imm(-nw - nw * i);
        }

        Inst headinst = func.enterBB.head;
        headinst.prepend(new Instack(headinst.BelongBB, Regs.s0));
        headinst.prepend(new Move(headinst.BelongBB, Regs.s0, Regs.sp));
        HashSet<PhysicalReg> needToSave = new HashSet<>(func.usedPhysicalRegister);
        needToSave.retainAll(Regs.calleeSave);

/*        System.out.println(Regs.calleeSave.size());
        System.out.println(needToSave.size());
        System.out.println("wow");*/

        headinst.prepend(new BinaryInst(headinst.BelongBB, BinaryInst.BinaryOp.SUB, Regs.sp, new Imm(frame.getFrameSize(needToSave.size()))));

//        HashSet<PhysicalReg> needToSave = new HashSet<>(func.usedPhysicalRegister);
//        needToSave.ReturnainAll(Regs.calleeSave);
        headinst = headinst.prev;



        needToSave.add(Regs.ra);



        for (PhysicalReg pr: needToSave)
            headinst.append(new Instack(headinst.BelongBB, pr));


        Return Return = (Return)func.leaveBB.tail;
        for (PhysicalReg pr: needToSave)
            Return.prepend(new Outstack(Return.BelongBB, pr));
        Return.prepend(new Leave(Return.BelongBB));
    }

    public void run() {
        for (Func func : irProgram.funcs) {
            processFunc(func);
        }
    }

}
