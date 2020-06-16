package Mxstar.IR;

import Mxstar.Configuration;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.Operand;
import Mxstar.Symbol.VarSymbol;
import Mxstar.IR.Operand.*;
import java.util.HashSet;

public class CorrectIR implements IRVisitor {
    IRProgram irProgram;

    @Override
    public void visit(IRProgram program) {
        this.irProgram = program;
        for (Func func: program.funcs) {
            func.accept(this);
        }
    }

    @Override
    public void visit(Func func) {
        for (BB BelongBB: func.basicblocks) {
            BelongBB.accept(this);
        }
    }

    @Override
    public void visit(BB BelongBB) {
        for (Inst inst = BelongBB.head; inst != null; inst = inst.next) {
            inst.accept(this);
        }
    }

    @Override
    public void visit(BinaryInst inst) {
    }

    @Override
    public void visit(UnaryInst inst) {

    }

    private PhysicalReg getPhyReg(Operand operand) {
        if (operand instanceof VirtualReg) {
            return ((VirtualReg)operand).allocatedPhyReg;
        } else if (operand instanceof PhysicalReg) {
            return (PhysicalReg)operand;
        } else {
            return null;
        }
    }

    @Override
    public void visit(Move inst) {
        if (inst.val instanceof AlloSpace && inst.tpos instanceof AlloSpace) {
            VirtualReg VirtualReg = new VirtualReg("");
            inst.prepend(new Move(inst.BelongBB, VirtualReg, inst.val));
            inst.val = VirtualReg;
        } else {
            if (Configuration.allocator == Configuration.ALLOCATOR.NAIVE_ALLOCATOR) {
                //if (Configuration.allocator == Configuration.ALLOCATOR.NAIVE_ALLOCATOR) {
                PhysicalReg pdest = getPhyReg(inst.tpos);
                PhysicalReg psrc = getPhyReg(inst.val);
                if (pdest != null && inst.val instanceof AlloSpace) {
                    VirtualReg virReg = new VirtualReg("");
                    inst.prepend(new Move(inst.BelongBB, virReg, inst.val));
                    inst.val = virReg;
                } else if (psrc != null && inst.tpos instanceof AlloSpace) {
                    VirtualReg virReg = new VirtualReg("");
                    inst.prepend(new Move(inst.BelongBB, virReg, inst.tpos));
                    inst.tpos = virReg;
                }
            }
        }
    }

    @Override
    public void visit(Instack inst) {

    }

    @Override
    public void visit(Outstack inst) {

    }

    @Override
    public void visit(Jump inst) {

    }

    @Override
    public void visit(Cjump inst) {
        if (inst.val1 instanceof Constant) {
            if (inst.val2 instanceof Constant) {
                inst.prepend(new Jump(inst.BelongBB, inst.doCompare()));
                inst.remove();
            } else {
                Operand tmp = inst.val1;
                inst.val1 = inst.val2;
                inst.val2 = tmp;
                inst.op = inst.getReverseOp();
            }
        }
    }

    @Override
    public void visit(Leave inst) {

    }

    @Override
    public void visit(Call inst) {       
        Func caller = inst.BelongBB.BelongFunc;
        Func callee = inst.nfunc;
//        System.out.println(callee);
//        HashSet<VarSymbol> callerUsed = caller.Global;
//        HashSet<VarSymbol> calleeUsed = callee.recursiveUsedGlobalSymbol;
        HashSet<VarSymbol> needToSave = new HashSet<>(caller.Global);


        needToSave.retainAll(callee.recursiveGlobal);
        if (Configuration.doGlobalAllocate) {
            for (VarSymbol variableSymbol : needToSave) {
//            if (calleeUsed.contains(variableSymbol)) {
                inst.prepend(new Move(inst.BelongBB, variableSymbol.VirtualReg.spillPlace, variableSymbol.VirtualReg));
                inst.prev.accept(this);
//            }
            }
        }
        while (inst.args.size() > 6 ) {
            inst.prepend(new Instack(inst.BelongBB, inst.args.removeLast()));
        }
        for (int i = inst.args.size() - 1; i >= 0 ; --i) {
            inst.prepend(new Move(inst.BelongBB, Regs.vargs.get(i), inst.args.get(i)));
            inst.prev.accept(this);
        }
        if (Configuration.doGlobalAllocate) {
            for (VarSymbol variableSymbol : needToSave) {
//            if (calleeUsed.contains(variableSymbol)) {
                inst.append(new Move(inst.BelongBB, variableSymbol.VirtualReg, variableSymbol.VirtualReg.spillPlace));
//                inst.prev.accept(this);
//            }
            }
        }
    }

    @Override
    public void visit(Return inst) {

    }


    @Override
    public void visit(Li inst) {

    }

    @Override
    public void visit(Inst inst) {

    }

    @Override
    public void visit(FuncAddr operand) {

    }

    @Override
    public void visit(VirtualReg operand) {

    }

    @Override
    public void visit(PhysicalReg operand) {
    }

    @Override
    public void visit(Imm operand) {

    }

    @Override
    public void visit(AlloSpace operand) {

    }

    @Override
    public void visit(Stack operand) {

    }

    @Override
    public void visit(ConstVal operand) {

    }

    @Override
    public void visit(Constant operand) {

    }
   /* @Override
    public void visit(Setcc inst) {
        if (inst.val1 instanceof Constant) {
            Operand tmp = inst.val1;
            inst.val1 = inst.val2;
            inst.val2 = tmp;
            inst.op = inst.getReverseOp();
        }
    }*/
}
