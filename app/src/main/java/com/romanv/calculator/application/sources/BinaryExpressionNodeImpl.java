package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.BinaryExpressionNode;
import com.romanv.calculator.application.api.BinaryOperator;
import com.romanv.calculator.application.api.ExpressionNode;
import com.romanv.calculator.application.api.ExpressionNodeType;
import com.romanv.calculator.application.api.ExpressionNodeVisitor;

class BinaryExpressionNodeImpl implements BinaryExpressionNode {

    BinaryExpressionNodeImpl(ExpressionNodeType type, BinaryOperator<Double> operator) {
        this.type = type;
        this.operator = operator;
    }

    @Override
    public void accept(ExpressionNodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public double evaluate() {
        return operator.evaluate(left.evaluate(), right.evaluate());
    }

    @Override
    public ExpressionNodeType getType() {
        return type;
    }

    @Override
    public ExpressionNode getLeftValue() {
        return left;
    }

    @Override
    public void setLeftValue(ExpressionNode value) {
        this.left = value;
    }

    @Override
    public ExpressionNode getRightValue() {
        return right;
    }

    @Override
    public void setRightValue(ExpressionNode value) {
        this.right = value;
    }

    @Override
    public boolean isValid() {
        return left != null && right != null;
    }

    private ExpressionNodeType type;

    private ExpressionNode left;
    private ExpressionNode right;

    private BinaryOperator<Double> operator;

}
