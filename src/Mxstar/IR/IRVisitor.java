package Mxstar.IR;

import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

public interface IRVisitor {
    void visit(VirtualReg vr);
    void visit(PhysicalReg vr);
    void visit(Imm imm);
    void visit(BB bb);
    void visit(Inst inst);
    void visit(Li inst);
    void visit(BinaryInst inst);
    void visit(Call inst);
    void visit(Cjump inst);
    void visit(Jump inst);
    void visit(Move inst);
    void visit(Return inst);
    void visit(UnaryInst inst);
    void visit(Instack inst);
    void visit(Outstack inst);
//    void visit(Setcc inst);
    void visit(Cdq inst);
    void visit(Leave inst);
    void visit(IRProgram irProgram);
    void visit(AlloSpace operand);
    void visit(ConstVal operand);
    void visit(Constant operand);
    void visit(Stack operand);
    void visit(FuncAddr operand);
    void visit(Func func);

}
