package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.ExpressionNodeType;
import com.romanv.calculator.application.api.ExpressionNodeVisitor;
import com.romanv.calculator.application.api.UnaryExpressionNode;

class UnaryExpressionNodeImpl implements UnaryExpressionNode {

    UnaryExpressionNodeImpl(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionNodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public ExpressionNodeType getType() {
        return ExpressionNodeType.Value;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    private double value;

}
