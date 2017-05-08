package com.romanv.calculator.application.api;

public interface ExpressionNode {

    double evaluate();

    ExpressionNodeType getType();

    boolean isValid();

    void accept(ExpressionNodeVisitor nodeVisitor);

}
