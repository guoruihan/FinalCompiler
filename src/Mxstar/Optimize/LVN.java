package Mxstar.Optimize;

import Mxstar.Configuration;
import Mxstar.IR.*;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

import java.util.HashMap;
import java.util.LinkedList;

import static Mxstar.IR.Inst.BinaryInst.BinaryOp.*;
import static Mxstar.IR.Regs.*;

public class LVN implements IRVisitor {
    private SVNTable table;
    private BB curBB;

    private void run() {
        for (Inst inst = curBB.head, nxtInst = inst; inst != null; inst = nxtInst) {
            nxtInst = nxtInst.next;
            inst.accept(this);
        }
    }

    private void copyPropagation(Inst inst) {
        LinkedList<Register> useRegs = inst.getUseRegs();
        LinkedList<Register> defRegs = inst.getDefRegs();
        useRegs.removeAll(defRegs);
        useRegs.clear();
        HashMap<Register, Register> renameMap = new HashMap<>();
        for (Register reg: useRegs) {
            int val = table.getOperandVal(reg);
            Operand operand = table.getValOperand(val);
            if (operand instanceof VirtualReg && operand != reg)
                renameMap.put(reg, (VirtualReg)operand);
        }
        inst.renameUseReg(renameMap);
    }

    public LVN(BB BelongBB, SVNTable table) {
        curBB = BelongBB;
        this.table = table;

        run();
    }

    @Override
    public void visit(IRProgram program) {

    }

    @Override
    public void visit(Func func) {

    }

    @Override
    public void visit(BB BelongBB) {

    }

    private int doBinaryary(BinaryInst.BinaryOp op, int lhs, int rhs) {
//        System.out.println(lhs);
//        System.out.println(rhs);
//        System.out.println("val");
        switch (op) {
            case MUL:
                return lhs * rhs;
            case DIV:
                if (rhs == 0)
                    return 1;
                return lhs / rhs;
            case REM:
                if (rhs == 0)
                    return 1;
                return lhs % rhs;
            case ADD:
                return lhs + rhs;
            case SUB:
                return lhs - rhs;
            case SLL:
                return (lhs << rhs);
            case SRL:
                return (lhs >> rhs);
            case OR:
                return (lhs | rhs);
            case AND:
                return (lhs & rhs);
            case XOR:
                return (lhs ^ rhs);
            default:
                return 0;
        }
    }

    private int doUnary(UnaryInst.UnaryOp op, int val) {
        switch (op) {
            case DEC:
                return val - 1;
            case INC:
                return val + 1;
            case NEG:
                return -val;
            case NOT:
                return ~val;
            default:
                return 0;
        }
    }

    @Override
    public void visit(BinaryInst inst) {
        copyPropagation(inst);
        int vrhs = table.getOperandVal(inst.val);
        int vlhs = (inst.op == MUL || inst.op == REM || inst.op == DIV) ? table.getOperandVal(va0) : table.getOperandVal(inst.tpos);
        Operand ilhs = table.getValOperand(vlhs);
        Operand irhs = table.getValOperand(vrhs);

        if (ilhs instanceof Imm && irhs instanceof Imm) {
            if (inst.op == BinaryInst.BinaryOp.DIV || inst.op == REM) {
                assert inst.prev instanceof Cdq;
                inst.prev.remove();
            }
            int res = doBinaryary(inst.op, ((Imm) ilhs).value, ((Imm) irhs).value);
//            System.out.println(res);
            if (inst.op == REM) {
                inst.replace(new Move(inst.BelongBB, vt4, new Imm(res)));
            } else if (inst.op == MUL || inst.op == DIV) {
                inst.replace(new Move(inst.BelongBB, va0, new Imm(res)));
            } else {
                inst.replace(new Move(inst.BelongBB, inst.tpos, new Imm(res)));
            }
            if (inst.op == REM) {
                table.putRegVal(va0);
                table.putRegVal(vt4, table.getImmVal(res));
            } else if (inst.op == BinaryInst.BinaryOp.MUL || inst.op == BinaryInst.BinaryOp.DIV) {
                table.putRegVal(va0, table.getImmVal(res));
                table.putRegVal(vt4);
            } else  {
                if (inst.tpos instanceof VirtualReg) {
                    table.putRegVal((VirtualReg) inst.tpos, table.getImmVal(res));
                }
            }
        } else {
            int keyval = table.getKeyVal(table.getOperandVal(inst.tpos), table.getOperandVal(inst.val), inst.op);
            Operand operand = table.getValOperand(keyval);
            if (inst.op == REM) {
                table.putRegVal(va0);
                table.putRegVal(vt4, keyval);
            } else if (inst.op == BinaryInst.BinaryOp.MUL || inst.op == BinaryInst.BinaryOp.DIV) {
                table.putRegVal(va0, keyval);
                table.putRegVal(vt4);
            } else if (inst.tpos instanceof VirtualReg) {
                table.putRegVal((VirtualReg) inst.tpos, keyval);
            }
        }
    }

    @Override
    public void visit(UnaryInst inst) {
        copyPropagation(inst);
        int val = table.getOperandVal(inst.tpos);
        int res = -1;
        Operand operand = table.getValOperand(val);
        if (operand instanceof Imm) {
            res = doUnary(inst.op, ((Imm) operand).value);
            inst.replace(new Move(inst.BelongBB, inst.tpos, new Imm(res)));
            res = table.getImmVal(res);
        } else {
            if (inst.op == UnaryInst.UnaryOp.INC) {
                res = table.getKeyVal(val, table.getImmVal(1), BinaryInst.BinaryOp.ADD);
            } else if (inst.op == UnaryInst.UnaryOp.DEC) {
                res = table.getKeyVal(val, table.getImmVal(1), BinaryInst.BinaryOp.SUB);
            }
            Operand reg = table.getValOperand(res);
        }
        if (inst.tpos instanceof VirtualReg) {
            if (res == -1)
                table.putRegVal((VirtualReg) inst.tpos);
            else {
                table.putRegVal((VirtualReg) inst.tpos, res);
            }
        }
    }

    @Override
    public void visit(Move inst) {
        copyPropagation(inst);
        int val = table.getOperandVal(inst.val);
//        System.out.println(val);
        Operand result = table.getValOperand(val);
        if (result instanceof Imm) {
            inst.replace(new Move(inst.BelongBB, inst.tpos, new Imm((Imm)result)));
        }
        if (inst.tpos instanceof VirtualReg) {
            table.putRegVal((VirtualReg) inst.tpos, val);
//            System.out.println(table.getValOperand(1) instanceof Imm);
        }
//        System.out.println(inst.tpos == va0);
//        System.out.println("finish move");
    }

    @Override
    public void visit(Instack inst) {
        copyPropagation(inst);
    }

    @Override
    public void visit(Outstack inst) {
        copyPropagation(inst);
        if (inst.tpos instanceof VirtualReg) {
            table.putRegVal((VirtualReg) inst.tpos);
        }
    }

    @Override
    public void visit(Jump inst) {

    }

    @Override
    public void visit(Cjump inst) {
        copyPropagation(inst);
        int lhs = table.getOperandVal(inst.val1);
        Operand rlhs = table.getValOperand(lhs);
        int rhs = table.getOperandVal(inst.val2);
        Operand rrhs = table.getValOperand(rhs);
        if (rlhs instanceof Imm) {
            inst.val1 = new Imm((Imm)rlhs);
        }
        if (rrhs instanceof Imm) {
            inst.val2 = new Imm((Imm)rrhs);
        }
    }

    @Override
    public void visit(Leave inst) {
        copyPropagation(inst);
    }

    @Override
    public void visit(Call inst) {
        copyPropagation(inst);
//        table.putRegVal(va0);
        for (VirtualReg reg: vcallerSave) {
            table.putRegVal(reg);
        }
    }

    @Override
    public void visit(Return inst) {
        copyPropagation(inst);
    }

    @Override
    public void visit(Cdq inst) {
        copyPropagation(inst);
        table.putRegVal(vt4);
    }

    @Override
    public void visit(Li inst) {
        copyPropagation(inst);
        if (inst.tpos instanceof VirtualReg) {
            table.putRegVal((VirtualReg) inst.tpos);
        }
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
}
