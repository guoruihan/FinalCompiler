package Mxstar.IR;

import Mxstar.IR.BB;
import Mxstar.IR.Func;
import Mxstar.IR.IRProgram;
import Mxstar.IR.IRVisitor;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

import static java.lang.System.exit;
import static Mxstar.IR.Regs.*;


public class OldPrintIR implements IRVisitor {
    public StringBuilder stringBuilder;
    public HashMap<BB, String> bbName;
    public HashMap<VirtualReg, String> varName;
    public HashMap<Stack, String> ssNames;
    public HashMap<ConstVal, String > sdNames;

    private boolean inDIV;
    private boolean inCMP;
    private boolean inMem;

    public BB nextbb = null;
    public boolean inLeaInst;
    public int bbCount = 0;
    public int varCount = 0;
    public int ssCount = 0;
    public int sdCount = 0;

    public static boolean showId = false;
    public static boolean showBlockHint = false;
    public static boolean showNasm = false;

    public OldPrintIR() {
        init();
    }

    public void init() {
        this.stringBuilder = new StringBuilder();
        this.bbName = new HashMap<>();
        this.varName = new HashMap<>();
        this.ssNames = new HashMap<>();
        this.sdNames = new HashMap<>();
        this.inLeaInst = false;
        this.inDIV = false;
        this.inCMP = false;
        this.inMem = false;
    }

    public String toString() {
        return stringBuilder.toString();
    }
    public void printTo(PrintStream out) {
        out.println(toString());
    }

    public String getBBName(BB bb) {
        if (!bbName.containsKey(bb)) {
            bbName.put(bb, "l_" + (bbCount++) + (showId ? "(1" + bb.BID + ")" : ""));
        }
        return bbName.get(bb);
    }
    public String getVarName(VirtualReg bb) {
        if (!varName.containsKey(bb)) {
            varName.put(bb, "v" + (varCount++) + (showId ? "(3" + bb.id + ")" : ""));
        }
        return varName.get(bb);
    }
    public String getSSName(Stack bb) {
        if (!ssNames.containsKey(bb)) {
            ssNames.put(bb, "stack[" + (ssCount++) + "]");
        }
        return ssNames.get(bb);
    }
    public String getSDName(ConstVal bb) {
        if (!sdNames.containsKey(bb)) {
            sdNames.put(bb, "g_" + (sdCount++));
        }
        return sdNames.get(bb);
    }

    @Override
    public void visit(IRProgram program) {
        if (showNasm) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("lib/c2nasm/lib.asm"));
                String line;
                while((line = br.readLine()) != null)
                    stringBuilder.append(line + "\n");
                stringBuilder.append(";====================================================");
                stringBuilder.append("\t section .text\n");
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }
        for (Func func: program.funcs)
            func.accept(this);

        if (showNasm) {
            stringBuilder.append("\t section .data\n");
            for (ConstVal staticData: program.constVal) {
                stringBuilder.append(getSDName(staticData) + "\n");
                if (staticData.init != null) {
                    stringBuilder.append("\tdq" + staticData.init.length() + "\n\tdb");
                    for (int i = 0; i < staticData.init.length(); ++i) {
                        Formatter formatter = new Formatter();
                        formatter.format("%02XH", (int)staticData.init.charAt(i));
                        stringBuilder.append(formatter);
                    }
                    stringBuilder.append("00H\n");
                } else {
                    stringBuilder.append("\tdb ");
                    for (int i = 0 ;i < staticData.bytes; ++i) {
                        if (i != 0) {
                            stringBuilder.append(", ");
                        }
                        stringBuilder.append("00H");
                    }
                    stringBuilder.append("\n");
                }
            }
        } else {
            for (ConstVal ConstVal : program.constVal) {
                stringBuilder.append(getSDName(ConstVal) + ":" + ConstVal.bytes + "bytes");
                if (ConstVal.init != null) {
                    stringBuilder.append("init: " + ConstVal.init);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("\n");
            }
        }

    }

    public String getNasmFuncName(Func func) {
        switch (func.type) {
            case Library:
                return "__" + func.name;
            case UserDefined:
                return "_" + func.name + "__myfunc";//in case the name conflict with some libc func
            case External:
                return func.name;
            default:
                return null;
        }
    }

    @Override
    public void visit(Func func) {

        if (showNasm) {
            stringBuilder.append(getNasmFuncName(func) + "\n");
        } else {
            stringBuilder.append("define " + getNasmFuncName(func) + " (");
            for (VirtualReg virReg: func.parameters) {
                virReg.accept(this);
                stringBuilder.append(",");
            }
            stringBuilder.append(")\n");
        }

        ArrayList<BB> reversePostOrder = new ArrayList<>(func.reversePostOrder);
        for (int i = 0; i < reversePostOrder.size(); ++i) {
            BB bb = reversePostOrder.get(i);
            try {
                nextbb = reversePostOrder.get(i + 1);
            } catch (Exception e) {
                nextbb = null;
            }

            bb.accept(this);
        }
        if (!showNasm)
            stringBuilder.append("}\n");
    }

    @Override
    public void visit(BB bb) {
        if (bb.head == null)
            return ;
        stringBuilder.append("\t" + getBBName(bb) + (showBlockHint ? "(" + bb.hint+ ")" : "") + ":\n");
        for (Inst inst = bb.head; inst != null; inst = inst.next) {
            inst.accept(this);
        }
    }

    @Override
    public void visit(BinaryInst inst) {
/*        if (inst.op == BinaryInst.BinaryOp.ADD || inst.op == BinaryInst.BinaryOp.SUB || inst.op == BinaryInst.BinaryOp.LES || inst.op == BinaryInst.BinaryOp.RES)
            if (inst.val instanceof Imm && ((Imm) inst.val).value == 0) {
                return ;
            }
        if ((inst.op == BinaryInst.BinaryOp.MUL)) {
            stringBuilder.append("\timul ");
            inst.val.accept(this);
            //tag1
            stringBuilder.append("\n");
            return ;
        }
        if ((inst.op == BinaryInst.BinaryOp.MOD) || (inst.op == BinaryInst.BinaryOp.DIV)) {
            stringBuilder.append("\tidiv ");
            inDIV = true;
            inst.val.accept(this);
            //tag1
            inDIV = false;
            stringBuilder.append("\n");
            stringBuilder.append("\tmovsxd t3, ecx\n\tmovsxd rdx, edx\n");

            return ;
        }
        if ((inst.op == BinaryInst.BinaryOp.LES) || (inst.op == BinaryInst.BinaryOp.RES)) {
            stringBuilder.append("\t" + inst.op.toString().toLowerCase() + " ");
            inst.tpos.accept(this);
            //tag1
            stringBuilder.append(", cl\n");
            return ;
        }
//        if (inst.val == null) {
//            System.out.println(inst.op.toString());
//        }

        stringBuilder.append("\t" + inst.op.toString().toLowerCase() + " ");
        inst.tpos.accept(this);
        //tag1
        stringBuilder.append(", ");
        inst.tpos.accept(this);
        //tag1
        stringBuilder.append(", ");
        inst.val.accept(this);
        //tag1
        stringBuilder.append("\n");*/
//tag
    }

    @Override
    public void visit(UnaryInst inst) {
        stringBuilder.append("\t" + inst.op.toString().toLowerCase() + " ");
        inst.tpos.accept(this);
        //tag1
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Move inst) {
        if (inst.tpos == inst.val)
            return ;
        stringBuilder.append("\tmv ");
        if(inst.tpos != null) {
            inst.tpos.accept(this);
            //tag1
        } else {
            stringBuilder.append("null ");
        }
        stringBuilder.append(", ");
        if (inst.val != null)
            inst.val.accept(this);
            //tag1
        else {
            stringBuilder.append("null ");
        }
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Instack inst) {
        stringBuilder.append("\tpush ");
        inst.val.accept(this);
        //tag1
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Outstack inst) {
        stringBuilder.append("\tpop ");
        inst.tpos.accept(this);
        //tag1
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Jump inst) {
        if (inst.toBB != nextbb) {
            stringBuilder.append("\tjmp " + getBBName(inst.toBB) + "\n");
        }
//        } else {
//            inst.remove();
//        }
    }

    @Override
    public void visit(Cjump inst) {
        String op = "j" + inst.op.toString().toLowerCase() + " ";
        if (showNasm) {
            stringBuilder.append("\tcmp ");
            inst.val1.accept(this);
            //tag1
            stringBuilder.append(", ");
            inst.val2.accept(this);
            //tag1
            stringBuilder.append("\n\t");
            stringBuilder.append(op + getBBName(inst.thenblock) + "\n");
            if (inst.elseblock != nextbb) {
                stringBuilder.append("\tjmp " + getBBName(inst.elseblock) + "\n");
            }
        } else {
            stringBuilder.append("\t" + op);
            inst.val1.accept(this);
            //tag1
            stringBuilder.append(", ");
            inst.val2.accept(this);
            //tag1
            stringBuilder.append(" then " + getBBName(inst.thenblock) + " else " + getBBName(inst.elseblock) + "\n");
        }
    }

    @Override
    public void visit(Leave inst) {
        stringBuilder.append("\tleave \n");
    }

    @Override
    public void visit(Call inst) {
        stringBuilder.append("\tcall ");
        stringBuilder.append(getNasmFuncName(inst.nfunc) + " ");

        if (!showNasm && inst.tpos != null) {
            inst.tpos.accept(this);
            //tag1
            stringBuilder.append(" = ");
        }

        if (!showNasm && inst.args != null) {
            for (Operand operand: inst.args) {
                stringBuilder.append(", ");
                if (operand == null) {
                    System.out.println(getNasmFuncName(inst.nfunc) + " ");
                }
                operand.accept(this);
                //tag1
            }
        }
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Return inst) {
        stringBuilder.append("\tret\n");
    }


    @Override
    public void visit(Li inst) {
        inLeaInst = true;
        stringBuilder.append("\tlea ");
        inst.tpos.accept(this);
        //tag1
        stringBuilder.append(", ");
        inst.val.accept(this);
        //tag1
        stringBuilder.append("\n");
        inLeaInst = false;
    }

/*   @Override
    public void visit(Setcc inst) {
        stringBuilder.append("\txor t3, t3\n\tcmp ");
        inst.val1.accept(this);
        //tag1
        stringBuilder.append(", ");
        inst.val2.accept(this);
        //tag1
        stringBuilder.append("\n\t");
        String op = "set" + inst.op.toString().toLowerCase() + " ";
        stringBuilder.append(op + "al\n");
        if (!(inst.tpos instanceof PhysicalReg && inst.tpos == t3)) {
            stringBuilder.append("\tmv ");
            inst.tpos.accept(this);
            //tag1
            stringBuilder.append(", t3\n");
        }
    }
*/
    @Override
    public void visit(Inst inst) {

    }

    @Override
    public void visit(FuncAddr operand) {
        stringBuilder.append(getNasmFuncName(operand.func));
    }

    @Override
    public void visit(VirtualReg operand) {
        if (operand.allocatedPhyReg != null) {
            operand.allocatedPhyReg.accept(this);
            //tag1
            varName.put(operand, operand.allocatedPhyReg.name);
        } else {
            stringBuilder.append(getVarName(operand));
        }
    }

    private  String toEightDigit(String name) {
//        System.out.println(name.charAt(1));
        if (name.charAt(1) > '0' && name.charAt(1) <= '9') {
            return (name + "b");
        } else {
            return (name.charAt(1) + "l");
        }
    }

    private String toSixTeenDigit(String name) {
        if (name.charAt(1) > '0' && name.charAt(1) <= '9') {
            return (name + "d");
        } else {
            return ("e" + name.substring(1));
        }
    }

    @Override
    public void visit(PhysicalReg operand) {
        if (inCMP) {
            stringBuilder.append(toEightDigit(operand.name));
        } else if (!inDIV || inMem) {
            stringBuilder.append(operand.name);
        } else {
//            stringBuilder.append(toSixTeenDigit(operand.name));
            stringBuilder.append(toSixTeenDigit(operand.name));
        }
    }

    @Override
    public void visit(Imm operand) {
        stringBuilder.append(operand.value);
    }

    @Override
    public void visit(AlloSpace operand) {
        boolean occur = false;
        if (!inLeaInst) {
            stringBuilder.append("qword ");
        }
        inMem = true;

        stringBuilder.append("[");
        if (operand.base != null) {
            operand.base.accept(this);
            //tag1
            occur = true;
        }
        if (operand.index != null) {
            if (occur)
                stringBuilder.append(" + ");
            operand.index.accept(this);
            //tag1
            if (operand.scale != 1) {
                stringBuilder.append(" * ");
                stringBuilder.append(operand.scale);
            }
            occur = true;
        }
        if (operand.constant != null) {
            Constant constant = operand.constant;
            if (constant instanceof ConstVal) {
                if (occur)
                    stringBuilder.append(" + ");
                constant.accept(this);
                //tag1
            } else if (constant instanceof Imm) {
                int val = ((Imm) constant).value;
                if (val != 0) {
                    if (occur && val >= 0)
                        stringBuilder.append(" + ");
                    stringBuilder.append(val);
                }
            }
        }
        inMem = false;

        stringBuilder.append("]");
    }

    @Override
    public void visit(Stack operand) {
        if (operand.base != null || operand.index != null || operand.constant != null) {
            visit((AlloSpace)operand);
            //tag1
        } else {
            stringBuilder.append(getSSName(operand));
        }
    }

    @Override
    public void visit(ConstVal operand) {
        stringBuilder.append(getSDName(operand));
    }

    @Override
    public void visit(Constant operand) {

    }



}
