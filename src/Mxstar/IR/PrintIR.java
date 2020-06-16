package Mxstar.IR;

import Mxstar.Configuration;
import Mxstar.IR.Inst.*;
import Mxstar.IR.Operand.*;

import java.awt.desktop.SystemSleepEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

import static java.lang.System.exit;
import static Mxstar.IR.Regs.*;


public class PrintIR implements IRVisitor {
    public StringBuilder stringBuilder;
    public StringBuilder nstr;
    public StringBuilder tmpstr;
    public StringBuilder tmp2str;
    public HashMap<BB, String> bbName;
    public HashMap<VirtualReg, String> varName;
    public HashMap<Stack, String> ssNames;
    public HashMap<ConstVal, String > sdNames;

    private boolean inDIV;
    private boolean inCMP;
    private boolean inMem;
    private boolean toReg = false;

    public BB nextbb = null;
    public boolean inLeaInst;
    public int bbCount = 0;
    public int varCount = 0;
    public int ssCount = 0;
    public int sdCount = 0;

    public static boolean showId = false;
    public static boolean showBlockHint = false;
    public static boolean showNasm = false;
    public static boolean Ltag=false;
    public static boolean Rtag=false;

    public PrintIR() {
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
        this.nstr = new StringBuilder();
        this.tmpstr = new StringBuilder();
        this.tmp2str = new StringBuilder();
        //this.nstr.delete(0, nstr.length());清空方法
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
       /* if (showNasm) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("builtin.s"));
                String line;
                while((line = br.readLine()) != null)
                    stringBuilder.append(line + "\n");
//                stringBuilder.append(";====================================================\n");
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }*/


        if (showNasm) {
            //System.err.println();
            for (ConstVal staticData: program.constVal)
                if(staticData.tag == 1) {
                    stringBuilder.append("\t"+".type\t" + staticData.hint + ",@object" + "\n");
                    stringBuilder.append("\t"+".section\t.bss" + "\n");
                    stringBuilder.append("\t"+".global\t" + staticData.hint + "\n");
                    stringBuilder.append("\t"+".p2align\t2" + "\n");
                    stringBuilder.append(staticData.hint + ":" + "\n");
                    stringBuilder.append("\t"+".L" + staticData.hint + "$local:" + "\n");
                    stringBuilder.append("\t"+".word\t0" + "\n");
                    stringBuilder.append("\t"+".size\t" + staticData.hint + ", 4\n\n");
                }
//            stringBuilder.append("section .data\n");
            for (ConstVal staticData: program.constVal) {
                if (staticData.tag == 0) {

                    String name = getSDName(staticData);
                    String value = staticData.init;
                    stringBuilder.append("\t"+".type\t" + name + ",@object"+ "\n");
                    stringBuilder.append("\t"+".section\t.rodata"+ "\n");
                    stringBuilder.append("\t"+name + ":"+ "\n");
                    stringBuilder.append("\t"+".asciz\t\""  );
                    for(int i=0;i<value.length();++i) {
                        if (value.charAt(i) == '\n') {
                            stringBuilder.append("\\n");
                        } else {
                            if (value.charAt(i) == '\t') {
                                stringBuilder.append("\\t");
                            } else {
                                if (value.charAt(i) == '\"') {
                                    stringBuilder.append("\\\"");
                                } else {
                                    if (value.charAt(i) == '\\') {
                                        stringBuilder.append("\\\\");
                                    } else {
                                        stringBuilder.append(value.charAt(i));
                                    }
                                }
                            }
                        }
                    }
                    stringBuilder.append("\""+ "\n");
                    stringBuilder.append("\t"+".size\t" + name + ", " + (value.length() + 1) + "\n\n");

                }
            }
        }

        stringBuilder.append(".text\n");
        for (Func func: program.funcs) {
            stringBuilder.append("\n");
            stringBuilder.append(".globl\t" + getNasmFuncName(func)+"\n");
            stringBuilder.append(".p2align\t1"+"\n");
            stringBuilder.append(".type\t" +getNasmFuncName(func)+ ",@function"+"\n");
            stringBuilder.append("\n");
            func.accept(this);
        }

    }

    public String getNasmFuncName(Func func) {
        if(func.type == Func.Type.Library && func.name.equals("initabcd")){
            return "main";
        }
        switch (func.type) {
            case Library:
                if(func.name.equals("println"))
                    return "puts";
                else
                    return func.name;
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
            stringBuilder.append(getNasmFuncName(func) + ":\n");
        } else {
            stringBuilder.append("define " + getNasmFuncName(func) + " (");
            for (VirtualReg virReg: func.parameters) {
                virReg.accept(this);
                stringBuilder.append(",");
            }
            stringBuilder.append(")\n");
        }
        //System.err.println(func.reversePostOrder.size());
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
        if (inst.op == BinaryInst.BinaryOp.ADD || inst.op == BinaryInst.BinaryOp.SUB || inst.op == BinaryInst.BinaryOp.SLL || inst.op == BinaryInst.BinaryOp.SRL)
            if (inst.val instanceof Imm && ((Imm) inst.val).value == 0) {
                return ;
            }
        /*if ((inst.op == BinaryInst.BinaryOp.MUL)) {
            nstr.append("\tmul ");
            nstr.append("t3, t3, ");
            inst.val.accept(this);
            //tag1
            nstr.append("\n");
            stringBuilder.append(nstr);
            nstr.delete(0,nstr.length());
            return ;
        }
        if ((inst.op == BinaryInst.BinaryOp.MOD) || (inst.op == BinaryInst.BinaryOp.DIV)) {
            nstr.append("\tidiv ");
            inDIV = true;
            inst.val.accept(this);
            //tag1
            inDIV = false;
            nstr.append("\n");
            nstr.append("\tmv t3, ecx\n\tmv t5, edx\n");
            stringBuilder.append(nstr);
            nstr.delete(0,nstr.length());
            return ;
        }*/
/*        if ((inst.op == BinaryInst.BinaryOp.LES) || (inst.op == BinaryInst.BinaryOp.RES)) {
            nstr.append("\t" + inst.op.toString().toLowerCase() + " ");
            inst.tpos.accept(this);
            //tag1
            nstr.append(", cl\n");
            stringBuilder.append(nstr);
            nstr.delete(0,nstr.length());
            return ;
        }*/
//        if (inst.val == null) {
//            System.out.println(inst.op.toString());
//        }


        if(inst.val instanceof Imm && inst.op == BinaryInst.BinaryOp.ADD) {
            nstr.append("\t" + inst.op.toString().toLowerCase() + "i" + " ");
        } else {
            nstr.append("\t" + inst.op.toString().toLowerCase() + " ");
        }
        inst.tpos.accept(this);
        //tag1
        nstr.append(", ");
        inst.tpos.accept(this);
        //tag1
        if(inst.op != BinaryInst.BinaryOp.ADD) {
            toReg = true;
        }
        nstr.append(", ");
        inst.val.accept(this);
        toReg = false;
        //tag1
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
    }

    @Override
    public void visit(UnaryInst inst) {
        if(inst.op == UnaryInst.UnaryOp.LOGICNEG){
            nstr.append("\t" + UnaryInst.UnaryOp.NOT.toString().toLowerCase() + " ");
            inst.tpos.accept(this);
            nstr.append(", ");
            inst.tpos.accept(this);
            //tag1
            nstr.append("\n");
            stringBuilder.append(nstr);
            nstr.delete(0, nstr.length());
        } else {
            if (inst.op == UnaryInst.UnaryOp.DEC || inst.op == UnaryInst.UnaryOp.INC) {
                //System.err.println(inst.tpos);
                nstr.append("\t" + "addi" + " ");


                inst.tpos.accept(this);

                //左参数需要修改
                nstr.append(", ");
                inst.tpos.accept(this);
                nstr.append(", ");
                if (inst.op == UnaryInst.UnaryOp.DEC)
                    nstr.append(-1);
                else
                    nstr.append(1);
                nstr.append("\n");

                if (inst.tpos instanceof AlloSpace) {
                    getpos(((AlloSpace) inst.tpos));
                    tmpstr.append("\tsw t0, 0(tp)\n");//t0是临时值
                    nstr.append(tmpstr);
                    tmpstr.delete(0, tmpstr.length());
                }

                stringBuilder.append(nstr);
                nstr.delete(0, nstr.length());
            } else {
                nstr.append("\t" + inst.op.toString().toLowerCase() + " ");
                inst.tpos.accept(this);
                nstr.append(", ");
                inst.tpos.accept(this);
                //tag1
                nstr.append("\n");
                stringBuilder.append(nstr);
                nstr.delete(0, nstr.length());
            }
        }
    }

    void getpos(AlloSpace operand){
        inMem = true;
        tmpstr.append("\tmv tp, zero\n");

        if (operand.index != null) {
            tmpstr.append("\tadd tp, tp, ");
            operand.index.accept(this);
            tmpstr.append("\n");
            //tag1
            if (operand.scale != 1) {
                tmpstr.append("\tli t2, ");
                tmpstr.append(operand.scale);
                tmpstr.append("\n");
                tmpstr.append("\tmul tp, tp, t2");
                tmpstr.append("\n");
            }
        }

        if (operand.base != null) {
            tmpstr.append("\tadd tp, tp, ");
            operand.base.accept(this);
            tmpstr.append("\n");
            //tag1
        }

        if (operand.constant != null) {
            Constant constant = operand.constant;
            if (constant instanceof ConstVal) {
                tmpstr.append("\tadd tp, tp, ");
                constant.accept(this);
                tmpstr.append("\n");
                //tag1
            } else if (constant instanceof Imm) {
                int val = ((Imm) constant).value;
                if (val != 0) {
                    tmpstr.append("\taddi tp, tp, ");
                    tmpstr.append(val);
                    tmpstr.append("\n");
                }
            }
        }

        inMem = false;
        stringBuilder.append(tmpstr);
        tmpstr.delete(0,tmpstr.length());
//tp里面是地址
    }

    public boolean GlobalVar(AlloSpace operand){
        return operand.base == null && operand.index == null;
    }

    @Override
    public void visit(Move inst) {
        //System.err.println(inst.tpos+ " " +inst.val);
        if (inst.tpos == inst.val)
            return ;
        if(inst.tpos instanceof AlloSpace) {
            if(!GlobalVar((AlloSpace)(inst.tpos))){
                getpos((AlloSpace)inst.tpos);
                nstr.append("\tsw ");
                toReg = true;
                if (inst.val != null)
                    inst.val.accept(this);
                toReg = false;
                nstr.append(", ");
                nstr.append("0(tp)");
                nstr.append("\n");
                stringBuilder.append(nstr);
                nstr.delete(0, nstr.length());
            } else {
                nstr.append("\tla tp, ");
                nstr.append(((AlloSpace)inst.tpos).name+"\n");
                nstr.append("\tsw ");
                toReg = true;
                if (inst.val != null)
                    inst.val.accept(this);
                toReg = false;
                nstr.append(", 0(tp)\n");
                stringBuilder.append(nstr);
                nstr.delete(0,nstr.length());
            }
        } else if(inst.val instanceof AlloSpace){

            if(!GlobalVar((AlloSpace)(inst.val))) {
                getpos((AlloSpace) inst.val);
                nstr.append("\tlw ");
                if (inst.tpos != null)
                    inst.tpos.accept(this);
                nstr.append(", ");
                nstr.append("0(tp)");
                nstr.append("\n");
                stringBuilder.append(nstr);
                nstr.delete(0, nstr.length());
            } else {
                nstr.append("\tla tp, ");
                nstr.append(((AlloSpace)inst.val).name+"\n");
                nstr.append("\tlw ");
                if (inst.tpos != null)
                    inst.tpos.accept(this);
                nstr.append(", 0(tp)\n");
                stringBuilder.append(nstr);
                nstr.delete(0,nstr.length());
            }
        } else {
            if(inst.val instanceof Imm) {
                nstr.append("\tli ");
            } else {
                nstr.append("\tmv ");
            }
            if (inst.tpos != null) {
                inst.tpos.accept(this);
                //tag1
            }
            nstr.append(", ");
            if (inst.val != null)
                inst.val.accept(this);

            nstr.append("\n");
            stringBuilder.append(nstr);
            nstr.delete(0, nstr.length());
        }
    }

    @Override
    public void visit(Instack inst) {
        /*
        nstr.append("\tpush ");
        inst.val.accept(this);
        //tag1
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
         */

        int nw = Configuration.REGISTER_WIDTH;
        nstr.append("\taddi sp, sp, ");
        nstr.append(-nw);
        nstr.append("\n\tsw ");
        if(inst.val instanceof Imm){
            stringBuilder.append("\tli t2, "+((Imm)inst.val).value+"\n");
            nstr.append("t2");
        } else {
            inst.val.accept(this);
        }
        //tag1
        nstr.append(", 0(sp)\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
    }

    @Override
    public void visit(Outstack inst) {
        /*
        nstr.append("\tpop ");
        inst.tpos.accept(this);
        //tag1
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
         */
        nstr.append("\tlw ");
        inst.tpos.accept(this);
        //tag1
        nstr.append(", 0(sp)\n");
        int nw = Configuration.REGISTER_WIDTH;
        nstr.append("\taddi sp, sp, ");
        nstr.append(nw);
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
    }

    @Override
    public void visit(Jump inst) {
        if (inst.toBB != nextbb) {
            stringBuilder.append("\tj " + getBBName(inst.toBB) + "\n");
        }
//        } else {
//            inst.remove();
//        }
    }

    @Override
    public void visit(Cjump inst) {
        String op = "b" + inst.op.toString().toLowerCase();
        boolean tag0 = false;
        if(inst.val2 instanceof Imm) {
            if (((Imm) inst.val2).value == 0)
                tag0 = true;
        }
        if(tag0){
            op += "z";
        }
        op += " ";
        nstr.append("\t" + op);
        inst.val1.accept(this);
        //tag1
        if(!tag0) {
            toReg = true;
            nstr.append(", ");
            inst.val2.accept(this);
            toReg = false;
        }
        //tag1
//            nstr.append("\n\t");

        nstr.append(", ");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
        stringBuilder.append(getBBName(inst.thenblock) + "\n");

        if (inst.elseblock != nextbb) {
            stringBuilder.append("\tj " + getBBName(inst.elseblock) + "\n");
        }
    }

    @Override
    public void visit(Leave inst) {
        stringBuilder.append("\tmv sp, s0\n");
        stringBuilder.append("\tlw s0, 0(sp)\n");//?
        int nw = Configuration.REGISTER_WIDTH;
        nstr.append("\taddi sp, sp, ");
        nstr.append(nw);
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
    }

    @Override
    public void visit(Call inst) {

        stringBuilder.append("\tcall ");
        stringBuilder.append(getNasmFuncName(inst.nfunc) + " ");


/*
        int nw = Configuration.REGISTER_WIDTH;
        nstr.append("\taddi sp, sp, ");
        nstr.append(-nw);
        nstr.append("\n\tsw ra");
        //tag1
        nstr.append(", 0(sp)\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());


        stringBuilder.append("\tcall ");
        stringBuilder.append(getNasmFuncName(inst.nfunc) + " ");


        nstr.append("\n\tlw ra");
        //tag1
        nstr.append(", 0(sp)\n");
        nstr.append("\taddi sp, sp, ");
        nstr.append(nw);
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
*/

 /*       if (!showNasm && inst.tpos != null) {
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
        }*/
        //useless (mytag)
        stringBuilder.append("\n");
    }

    @Override
    public void visit(Return inst) {
        /*
        nstr.append("\tlw ra");
        //tag1
        nstr.append(", 0(sp)\n");
        int nw = Configuration.REGISTER_WIDTH;
        nstr.append("\taddi sp, sp, ");
        nstr.append(nw);
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());*/
        stringBuilder.append("\tret\n");
    }


    @Override
    public void visit(Li inst) {
        inLeaInst = true;
        nstr.append("\tla ");
        inst.tpos.accept(this);
        //tag1
        nstr.append(", ");
        inst.val.accept(this);
        //tag1
        nstr.append("\n");
        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
        inLeaInst = false;
    }

   /* @Override
    public void visit(Setcc inst) {
        stringBuilder.append("\txor a0, a0\n\tcmp ");
        inst.val1.accept(this);
        //tag1
        nstr.append(", ");
        inst.val2.accept(this);
        //tag1
        nstr.append("\n\t");

        stringBuilder.append(nstr);
        nstr.delete(0,nstr.length());
        String op = "set" + inst.op.toString().toLowerCase() + " ";
        stringBuilder.append(op + "al\n");
        if (!(inst.tpos instanceof PhysicalReg && inst.tpos == a0)) {
            stringBuilder.append("\tmv ");
            inst.tpos.accept(this);
            //tag1
            stringBuilder.append(", a0\n");
        }
    }*/

    @Override
    public void visit(Inst inst) {

    }

    @Override
    public void visit(FuncAddr operand) {
        stringBuilder.append(getNasmFuncName(operand.func));
    }

    @Override
    public void visit(VirtualReg operand) {
        if (!inMem) {
            if (operand.allocatedPhyReg != null) {
                operand.allocatedPhyReg.accept(this);
                //tag1
                varName.put(operand, operand.allocatedPhyReg.name);
            } else {
                nstr.append(getVarName(operand));
            }
        } else {
            if (operand.allocatedPhyReg != null) {
                operand.allocatedPhyReg.accept(this);
                //tag1
                varName.put(operand, operand.allocatedPhyReg.name);
            } else {
                tmpstr.append(getVarName(operand));
            }
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
        if(!inMem){
            if (inCMP) {
                nstr.append(toEightDigit(operand.name));
            } else if (!inDIV || inMem) {
                nstr.append(operand.name);
            } else {
                //            nstr.append(toSixTeenDigit(operand.name));
                nstr.append(toSixTeenDigit(operand.name));
            }
        } else {
            if (inCMP) {
                tmpstr.append(toEightDigit(operand.name));
            } else if (!inDIV || inMem) {
                tmpstr.append(operand.name);
            } else {
                //            nstr.append(toSixTeenDigit(operand.name));
                tmpstr.append(toSixTeenDigit(operand.name));
            }
        }
    }

    @Override
    public void visit(Imm operand) {
        if(toReg){
            tmpstr.append("\tli t1, ");
            tmpstr.append(operand.value);
            tmpstr.append("\n");
            stringBuilder.append(tmpstr);
            tmpstr.delete(0,tmpstr.length());
            nstr.append("t1");
        } else {
            nstr.append(operand.value);
        }
    }

    @Override
    public void visit(AlloSpace operand) {
        if(GlobalVar(operand)) {
            tmpstr.append("\tla tp, ");
            tmpstr.append(operand.name+"\n");
            tmpstr.append("\tlw t0, 0(tp)\n");
/*            System.out.println(operand.name);
            System.out.println(operand);
            System.out.println(operand.constant);*/
            stringBuilder.append(tmpstr);
            tmpstr.delete(0,tmpstr.length());
            nstr.append("t0");
            return;
        }//全局变量
        getpos(operand);
        tmpstr.append("\tlw t0, 0(tp)\n");//t0是临时值
        stringBuilder.append(tmpstr);
        tmpstr.delete(0,tmpstr.length());
        nstr.append("t0");
/*        boolean occur = false;
        if (!inLeaInst) {
            nstr.append("qword ");
        }
        inMem = true;

        nstr.append("[");
        if (operand.base != null) {
            operand.base.accept(this);
            //tag1
            occur = true;
        }
        if (operand.index != null) {
            if (occur)
                nstr.append(" + ");
            operand.index.accept(this);
            //tag1
            if (operand.scale != 1) {
                nstr.append(" * ");
                nstr.append(operand.scale);
            }
            occur = true;
        }
        if (operand.constant != null) {
            Constant constant = operand.constant;
            if (constant instanceof ConstVal) {
                if (occur)
                    nstr.append(" + ");
                constant.accept(this);
                //tag1
            } else if (constant instanceof Imm) {
                int val = ((Imm) constant).value;
                if (val != 0) {
                    if (occur && val >= 0)
                        nstr.append(" + ");
                    nstr.append(val);
                }
            }
        }
        inMem = false;

        nstr.append("]");*/
    }

    @Override
    public void visit(Stack operand) {
        if (operand.base != null || operand.index != null || operand.constant != null) {
            visit((AlloSpace)operand);
            //tag1
        } else {
            nstr.append(getSSName(operand));
        }
    }

    @Override
    public void visit(ConstVal operand) {
        String name;
        if(operand.tag == 1) {
            name = operand.hint;
        } else {
            name = getSDName(operand);
        }
        tmp2str.append("\tla t2, ");
        tmp2str.append(name+"\n");
        stringBuilder.append(tmp2str);
        tmp2str.delete(0,tmp2str.length());
        if (!inMem) {
            nstr.append("t2");
        } else {
            tmpstr.append("t2");
        }
    }

    @Override
    public void visit(Constant operand) {

    }



}