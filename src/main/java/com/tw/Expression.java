package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.tw.Operator.get;
import static java.lang.Double.parseDouble;

public class Expression {
    private final List<Token> tokens;
    private final Stack<Token> stack;

    public Expression(String postfixExpression) {
        tokens = parse(postfixExpression);
        stack = new Stack<>();
    }

    private List<Token> parse(String postfixExpression) {
        List<Token> tokens = new ArrayList<>();
        for (String token : postfixExpression.split("")) {
            if (isOperator(token)) {
                tokens.add(get(token));
            } else {
                tokens.add(new Operand(token));
            }
        }

        return tokens;
    }

    public double evaluate() {
        for (Token token : tokens) {
            if (token.isOperand()) {
                stack.push(token);
            }

            if (token.isOperator()) {
                Token operand1 = stack.pop();
                Token operand2 = stack.pop();
                Token result = perform(token.getValue(), operand1.getValue(), operand2.getValue());
                stack.push(result);
            }
        }
        return parseDouble(stack.pop().getValue());
    }

    private Token perform(String operator, String operand1, String operand2) {
        double result = get(operator)
                .perform(parseDouble(operand2), parseDouble(operand1));
        return new Operand(Double.toString(result));
    }

    private boolean isOperator(String token) {
        return Operator.isOperator(token);
    }
}
