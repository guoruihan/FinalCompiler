package Mxstar.Ast;

import Mxstar.FrontEnd.*;

public abstract class AstNode {
    public Position position = null;
    public abstract void accept(AstVisitor visitor);
}