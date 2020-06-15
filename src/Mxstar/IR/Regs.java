package Mxstar.IR;

import java.util.LinkedList;
import Mxstar.IR.Operand.*;

public class Regs {
    public static VirtualReg vzero;
    public static VirtualReg vra;
    public static VirtualReg vsp;
    public static VirtualReg vgp;
    public static VirtualReg vtp;
    public static VirtualReg vt0;
    public static VirtualReg vt1;
    public static VirtualReg vt2;
    public static VirtualReg vs0;
    public static VirtualReg vs1;
    public static VirtualReg va0;
    public static VirtualReg va1;
    public static VirtualReg va2;
    public static VirtualReg va3;
    public static VirtualReg va4;
    public static VirtualReg va5;
    public static VirtualReg va6;
    public static VirtualReg va7;
    public static VirtualReg vs2;
    public static VirtualReg vs3;
    public static VirtualReg vs4;
    public static VirtualReg vs5;
    public static VirtualReg vs6;
    public static VirtualReg vs7;
    public static VirtualReg vs8;
    public static VirtualReg vs9;
    public static VirtualReg vs10;
    public static VirtualReg vs11;
    public static VirtualReg vt3;
    public static VirtualReg vt4;
    public static VirtualReg vt5;
    public static VirtualReg vt6;

    public static PhysicalReg zero;
    public static PhysicalReg ra;
    public static PhysicalReg sp;
    public static PhysicalReg gp;
    public static PhysicalReg tp;
    public static PhysicalReg t0;
    public static PhysicalReg t1;
    public static PhysicalReg t2;
    public static PhysicalReg s0;
    public static PhysicalReg s1;
    public static PhysicalReg a0;
    public static PhysicalReg a1;
    public static PhysicalReg a2;
    public static PhysicalReg a3;
    public static PhysicalReg a4;
    public static PhysicalReg a5;
    public static PhysicalReg a6;
    public static PhysicalReg a7;
    public static PhysicalReg s2;
    public static PhysicalReg s3;
    public static PhysicalReg s4;
    public static PhysicalReg s5;
    public static PhysicalReg s6;
    public static PhysicalReg s7;
    public static PhysicalReg s8;
    public static PhysicalReg s9;
    public static PhysicalReg s10;
    public static PhysicalReg s11;
    public static PhysicalReg t3;
    public static PhysicalReg t4;
    public static PhysicalReg t5;
    public static PhysicalReg t6;

    public static LinkedList<PhysicalReg> allRegs;
    public static LinkedList<PhysicalReg> calleeSave;
    public static LinkedList<PhysicalReg> callerSave;
    public static LinkedList<PhysicalReg> args;

    public static LinkedList<VirtualReg> vallRegs;
    public static LinkedList<VirtualReg> vcalleeSave;
    public static LinkedList<VirtualReg> vcallerSave;
    public static LinkedList<VirtualReg> vargs;

    public static void init() {
        allRegs = new LinkedList<>();
        calleeSave = new LinkedList<>();
        callerSave = new LinkedList<>();
        args = new LinkedList<>();
        vallRegs = new LinkedList<>();
        vcalleeSave = new LinkedList<>();
        vcallerSave = new LinkedList<>();
        vargs = new LinkedList<>();

        String[] name = {"zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6" };
        Boolean[] isCallerSave = new Boolean[]{null,true,false,null,null,true,true,true,false,false,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,true,true,true,true};
        for (int i = 0; i < 32; ++i) {
            PhysicalReg pr = new PhysicalReg();
            VirtualReg vr = new VirtualReg("v" + name[i]);
            pr.name = name[i];
            vr.allocatedPhyReg = pr;
            allRegs.add(pr);
            vallRegs.add(vr);
            if (isCallerSave[i] != null) {
                if (isCallerSave[i]) {
                    callerSave.add(pr);
                    vcallerSave.add(vr);
                } else {
                    calleeSave.add(pr);
                    vcalleeSave.add(vr);
                }
            }
        }
        /*
        rax = allRegs.get(0);
        rbx = allRegs.get(1);
        rcx = allRegs.get(2);
        rdx = allRegs.get(3);
        rbp = allRegs.get(4);
        rsp = allRegs.get(5);
        rsi = allRegs.get(6);
        rdi = allRegs.get(7);
        r8 = allRegs.get(8);
        r9 = allRegs.get(9);
        r10 = allRegs.get(10);
        r11 = allRegs.get(11);
        r12 = allRegs.get(12);
        r13 = allRegs.get(13);
        r14 = allRegs.get(14);
        r15 = allRegs.get(15);
*/
        zero = allRegs.get(0);
        ra = allRegs.get(1);
        sp = allRegs.get(2);
        gp = allRegs.get(3);
        tp = allRegs.get(4);
        t0 = allRegs.get(5);
        t1 = allRegs.get(6);
        t2 = allRegs.get(7);
        s0 = allRegs.get(8);
        s1 = allRegs.get(9);
        a0 = allRegs.get(10);
        a1 = allRegs.get(11);
        a2 = allRegs.get(12);
        a3 = allRegs.get(13);
        a4 = allRegs.get(14);
        a5 = allRegs.get(15);
        a6 = allRegs.get(16);
        a7 = allRegs.get(17);
        s2 = allRegs.get(18);
        s3 = allRegs.get(19);
        s4 = allRegs.get(20);
        s5 = allRegs.get(21);
        s6 = allRegs.get(22);
        s7 = allRegs.get(23);
        s8 = allRegs.get(24);
        s9 = allRegs.get(25);
        s10 = allRegs.get(26);
        s11 = allRegs.get(27);
        t3 = allRegs.get(28);
        t4 = allRegs.get(29);
        t5 = allRegs.get(30);
        t6 = allRegs.get(31);

        vzero = vallRegs.get(0);
        vra = vallRegs.get(1);
        vsp = vallRegs.get(2);
        vgp = vallRegs.get(3);
        vtp = vallRegs.get(4);
        vt0 = vallRegs.get(5);
        vt1 = vallRegs.get(6);
        vt2 = vallRegs.get(7);
        vs0 = vallRegs.get(8);
        vs1 = vallRegs.get(9);
        va0 = vallRegs.get(10);
        va1 = vallRegs.get(11);
        va2 = vallRegs.get(12);
        va3 = vallRegs.get(13);
        va4 = vallRegs.get(14);
        va5 = vallRegs.get(15);
        va6 = vallRegs.get(16);
        va7 = vallRegs.get(17);
        vs2 = vallRegs.get(18);
        vs3 = vallRegs.get(19);
        vs4 = vallRegs.get(20);
        vs5 = vallRegs.get(21);
        vs6 = vallRegs.get(22);
        vs7 = vallRegs.get(23);
        vs8 = vallRegs.get(24);
        vs9 = vallRegs.get(25);
        vs10 = vallRegs.get(26);
        vs11 = vallRegs.get(27);
        vt3 = vallRegs.get(28);
        vt4 = vallRegs.get(29);
        vt5 = vallRegs.get(30);
        vt6 = vallRegs.get(31);

        args.add(a0);
        args.add(a1);
        args.add(a2);
        args.add(a3);
        args.add(a4);
        args.add(a5);
        args.add(a6);
        args.add(a7);

        vargs.add(va0);
        vargs.add(va1);
        vargs.add(va2);
        vargs.add(va3);
        vargs.add(va4);
        vargs.add(va5);
        vargs.add(va6);
        vargs.add(va7);
    }
}
