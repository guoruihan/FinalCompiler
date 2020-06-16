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


public class BuildStack {
    class Frame{
        public LinkedList<Stack> parameters = new LinkedList<>();
        public LinkedList<Stack> temporaries = new LinkedList<>();
        public int getFrameSize(int offset) {
//            System.out.println(parameters.size());
//            System.out.println(temporaries.size() + "\n");
            int bytes = Configuration.REGISTER_WIDTH * (parameters.size() + temporaries.size());

            return bytes;
        }
    }

    public IRProgram irProgram;
    public HashMap<Func,  Frame> frameMap;

    public BuildStack(IRProgram irProgram) {
        this.irProgram = irProgram;
        frameMap = new HashMap<>();
    }

    void leavefunc(HashSet<PhysicalReg> needToSave, Inst headinst,Func func){


        for (PhysicalReg pr: needToSave)
            headinst.append(new Instack(headinst.BelongBB, pr));

        Return Return = (Return)func.leaveBB.tail;

        for (PhysicalReg pr: needToSave)
            Return.prepend(new Outstack(Return.BelongBB, pr));

        Return.prepend(new Leave(Return.BelongBB));
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
            }

        }

        int nw = Configuration.REGISTER_WIDTH;

        frame.temporaries.addAll(slots);
        for (int i = 0; i < frame.parameters.size(); ++i) {
            Stack Stack = frame.parameters.get(i);
            Stack.base = Regs.s0;
        }

        for (int i = 0; i < frame.parameters.size(); ++i) {
            Stack Stack = frame.parameters.get(i);
            Stack.constant = new Imm(nw*2 + nw * i);
        }

        for (int i = 0; i < frame.temporaries.size(); ++i) {
            Stack Stack = frame.temporaries.get(i);
            Stack.base = Regs.s0;
        }

        for (int i = 0; i < frame.temporaries.size(); ++i) {
            Stack Stack = frame.temporaries.get(i);
            Stack.constant = new Imm(-nw - nw * i);
        }

        Inst headinst = func.enterBB.head;
        BB tmpBB = headinst.BelongBB;
        headinst.prepend(new Instack(tmpBB, Regs.s0));
        headinst.prepend(new Move(tmpBB, Regs.s0, Regs.sp));
        HashSet<PhysicalReg> needToSave = new HashSet<>(func.usedPhysicalRegister);
        needToSave.retainAll(Regs.calleeSave);

/*        System.out.println(Regs.calleeSave.size());
        System.out.println(needToSave.size());
        System.out.println("wow");*/
        Imm tmpImm = new Imm(frame.getFrameSize(needToSave.size()));
        headinst.prepend(new BinaryInst(headinst.BelongBB, BinaryInst.BinaryOp.SUB, Regs.sp, tmpImm));
        headinst = headinst.prev;



        needToSave.add(Regs.ra);


        leavefunc(needToSave,headinst,func);
    }

    public void run() {
        for (Func func : irProgram.funcs) {
            processFunc(func);
        }
    }

}
