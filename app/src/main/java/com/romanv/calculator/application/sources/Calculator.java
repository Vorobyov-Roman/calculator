package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;
import com.romanv.calculator.application.api.Environment;
import com.romanv.calculator.application.api.ExpressionNode;

public class Calculator extends EventEmitter<Double> {

    public Calculator(Environment environment) {
        this.environment = environment;
        result = 0.0;
    }

    void onInputChanged(String expression) {
        if (expression.isEmpty()) {
            reset();
            return;
        }

        try {
            result = calculate(expression);
            notifySubscribers();
        }
        catch (ParseError e) {
            reset();
        }
    }

    void subscribeToOutputChange(Callback<Double> callback) {
        addSubscriber(callback);
    }

    void reset() {
        result = 0.0;
        notifySubscribers();
    }

    @Override
    public Double getValue() {
        return result;
    }

    public double calculate(String expression) throws ParseError {
        Tokenizer tokenizer = new Tokenizer(environment);
        ExpressionNodesFactory factory = new ExpressionNodesFactory(environment);
        ExpressionTreeBuilder builder = new ExpressionTreeBuilder();

        tokenizer.setString(expression);
        tokenizer.forEachToken((token) -> {
            ExpressionNode node = factory.createNode(token);
            builder.pushNode(node);
        });

        ExpressionNode rootNode = builder.getRoot();
        return rootNode.evaluate();
    }

    private Environment environment;

    private double result;

}
