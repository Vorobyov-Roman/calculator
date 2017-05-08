package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.BinaryExpressionNode;
import com.romanv.calculator.application.api.ExpressionNode;
import com.romanv.calculator.application.api.ExpressionNodeType;
import com.romanv.calculator.application.api.ExpressionNodeVisitor;
import com.romanv.calculator.application.api.UnaryExpressionNode;
import com.romanv.calculator.application.api.UnaryPredicate;

import java.util.LinkedList;
import java.util.List;

class ExpressionTreeBuilder implements ExpressionNodeVisitor {

    ExpressionTreeBuilder() {
        nodesList = new LinkedList<>();
    }

    void pushNode(ExpressionNode node) {
        nodesList.add(node);
    }

    ExpressionNode getRoot() throws ParseError {
        if (nodesList.isEmpty())
            return null;

        int precedenceLevel = 1;
        while (nodesList.size() != 1)
            reduce(precedenceLevel++);

        return nodesList.get(0);
    }

    @Override
    public void visit(UnaryExpressionNode node) {
        result = true;
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        try {
            int index = nodesList.indexOf(node);
            ExpressionNode right = nodesList.remove(index + 1);
            ExpressionNode left = nodesList.remove(index - 1);

            node.setLeftValue(left);
            node.setRightValue(right);

            result = true;
        }
        catch (Exception e) {
            result = false;
        }
    }

    private void reduce(int precedenceLevel) throws ParseError {
        UnaryPredicate<ExpressionNode> predicate = getPredicate(precedenceLevel);

        result = true;
        ExpressionNode node;

        do {
            node = null;

            for (int i = 0; i != nodesList.size(); ++i) {
                ExpressionNode currentNode = nodesList.get(i);

                if (!currentNode.isValid() && predicate.test(currentNode)) {
                    node = currentNode;
                    break;
                }
            }

            if (node != null)
                node.accept(this);

            if (!result)
                throw new ParseError();
        }
        while (node != null);
    }

    private UnaryPredicate<ExpressionNode> getPredicate(int precedenceLevel) throws ParseError {
        switch (precedenceLevel) {
            case 1:
                return (node) ->    node.getType() == ExpressionNodeType.Multiplication ||
                                    node.getType() == ExpressionNodeType.Division;

            case 2:
                return (node) ->    node.getType() == ExpressionNodeType.Addition ||
                                    node.getType() == ExpressionNodeType.Subtraction;

            default:
                throw new ParseError();
        }
    }

    private List<ExpressionNode> nodesList;

    private boolean result;

}
