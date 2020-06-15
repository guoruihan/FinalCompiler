import Mxstar.IR.*;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

import static Mxstar.IR.Regs.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class EasyAllo {

/*
    public IRProgram irProgram;
    LinkedList<PhysicalReg> generalRegisters = new LinkedList<>();

    public EasyAllo(IRProgram irProgram) {
        this.irProgram = irProgram;
        generalRegisters.add(rbx);
        generalRegisters.add(r10);
        generalRegisters.add(r11);
        generalRegisters.add(r12);
        generalRegisters.add(r13);
        generalRegisters.add(r14);
        generalRegisters.add(r15);

        for (Func func: irProgram.funcs) {
            processFunc(func);
        }
    }

    public PhysicalReg getPhyReg(Operand operand) {
        if (operand instanceof VirtualReg) {
            return ((VirtualReg)operand).allocatedPhyReg;
        } else if (operand instanceof PhysicalReg) {
            return (PhysicalReg)operand;
        } else {
            return null;
        }
    }

    public void processFunc(Func func) {
        for (BB bb: func.basicblocks) {
            for (Inst inst = bb.head; inst != null; inst = inst.next) {
                if (inst instanceof Call)
                    continue;
                HashMap<Register, Register> renameMap = new HashMap<>();
                HashSet<Register> allregs = new HashSet<>();
                HashSet<Register> usedregs = new HashSet<>(inst.getUseRegs());
                HashSet<Register> defregs = new HashSet<>(inst.getDefRegs());
                allregs.addAll(usedregs);
                allregs.addAll(defregs);
                for (Register register: allregs) {
                    VirtualReg virReg = (VirtualReg) register;
                    if (virReg.allocatedPhyReg != null)
                        continue;
                    if (virReg.spillPlace == null)
                        virReg.spillPlace = new Stack(virReg.name);
                }

                if (inst instanceof Move) {
                    Move mov = (Move)inst;
                    Address tpos = mov.tpos;
                    Operand val = mov.val;
                    PhysicalReg pdest = getPhyReg(tpos);
                    PhysicalReg psrc = getPhyReg(val);
                    if (pdest != null && psrc != null) {
                        mov.tpos = pdest;
                        mov.val = psrc;
                        continue;
                    } else if (pdest != null){
                        mov.tpos = pdest;
                        if (mov.val instanceof VirtualReg) {
                            mov.val = ((VirtualReg) mov.val).spillPlace;
                        }
                    } else if (psrc != null) {
                        mov.val = psrc;
                        if (mov.tpos instanceof VirtualReg) {
                            mov.tpos = ((VirtualReg) mov.tpos).spillPlace;
                        }
                    }
                }

                int cnt = 0;
                for (Register regs: allregs) {
                    if (!renameMap.containsKey(regs)) {
                        PhysicalReg phyReg = ((VirtualReg)regs).allocatedPhyReg;
                        if (phyReg == null) {
                            renameMap.put(regs, generalRegisters.get(cnt++));
                        } else {
                            renameMap.put(regs, phyReg);
                        }
                    }
                }

                inst.renameUseReg(renameMap);
                inst.renameDefReg(renameMap);
                for (Register regs: usedregs) {
                    if (((VirtualReg)regs).allocatedPhyReg == null) {
                        inst.prepend(new Move(bb, renameMap.get(regs), ((VirtualReg) regs).spillPlace));
                    }
                }

                for (Register regs: defregs) {
                    if (((VirtualReg)regs).allocatedPhyReg == null) {
                        inst.append(new Move(bb, ((VirtualReg) regs).spillPlace, renameMap.get(regs)));
                        inst = inst.next;
                    }
                }
            }


        }
    }
*/
}
