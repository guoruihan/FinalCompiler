package Mxstar.Ast.Expr;

import Mxstar.Ast.*;
import Mxstar.Symbol.*;

public abstract class Expr extends AstNode {
    public VarType type;
    public boolean modifiable;
}