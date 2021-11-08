package com.tw;


import com.tw.expression.InfixExpression;
import com.tw.expression.PostfixExpression;
import com.tw.parser.ExpressionParser;

public class Calculator {
    private final ExpressionParser expressionParser;

    public Calculator() {
        expressionParser = new ExpressionParser();
    }

    public double calculate(String expression) {
        InfixExpression infixExpression = new InfixExpression(expression, expressionParser); // TODO: can be a dependency
        PostfixExpression postfixExpression = infixExpression.toPostfix();
        return postfixExpression.evaluate();
    }
}
