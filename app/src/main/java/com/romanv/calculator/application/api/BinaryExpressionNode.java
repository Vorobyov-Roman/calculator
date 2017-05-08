package com.romanv.calculator.application.api;

public interface BinaryExpressionNode extends ExpressionNode {

    ExpressionNode getLeftValue();
    void setLeftValue(ExpressionNode value);

    ExpressionNode getRightValue();
    void setRightValue(ExpressionNode value);

}
