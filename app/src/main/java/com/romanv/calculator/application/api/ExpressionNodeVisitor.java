package com.romanv.calculator.application.api;

public interface ExpressionNodeVisitor {

    void visit(UnaryExpressionNode node);

    void visit(BinaryExpressionNode node);

}
