package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Environment;
import com.romanv.calculator.application.api.ExpressionNode;
import com.romanv.calculator.application.api.ExpressionNodeType;

public class ExpressionNodesFactory {

    public ExpressionNodesFactory(Environment environment) {
        this.environment = environment;
    }

    public ExpressionNode createNode(String token) throws UnexpectedTokenException {
        if (token.equals(environment.getAdditionSymbol())) {
            return createAdditionNode();
        }
        if (token.equals(environment.getSubtractionSymbol())) {
            return createSubtractionNode();
        }
        if (token.equals(environment.getMultiplicationSymbol())) {
            return createMultiplicationNode();
        }
        if (token.equals(environment.getDivisionSymbol())) {
            return createDivisionNode();
        }

        return createUnaryNode(token);
    }

    private ExpressionNode createAdditionNode() {
        return new BinaryExpressionNodeImpl(
            ExpressionNodeType.Addition,
            (left, right) -> left + right
        );
    }

    private ExpressionNode createSubtractionNode() {
        return new BinaryExpressionNodeImpl(
            ExpressionNodeType.Subtraction,
            (left, right) -> left - right
        );
    }

    private ExpressionNode createMultiplicationNode() {
        return new BinaryExpressionNodeImpl(
            ExpressionNodeType.Multiplication,
            (left, right) -> left * right
        );
    }

    private ExpressionNode createDivisionNode() {
        return new BinaryExpressionNodeImpl(
            ExpressionNodeType.Division,
            (left, right) -> left / right
        );
    }

    private ExpressionNode createUnaryNode(String token) throws UnexpectedTokenException {
        try {
            return new UnaryExpressionNodeImpl(Double.valueOf(token));
        }
        catch (NumberFormatException e) {
            throw new UnexpectedTokenException(e);
        }
    }

    private Environment environment;

}
