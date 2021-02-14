package com.tw;

import com.tw.exception.DivideByZeroException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Double.parseDouble;

public class Expression {
    private final List<Token> tokens;
    private final Stack<Token> stack;
    private final Calculator calculator;

    public Expression(String postfixExpression) {
        tokens = parse(postfixExpression);
        calculator = new Calculator(); // TODO: use mock
        stack = new Stack<>();
    }

    private List<Token> parse(String postfixExpression) {
        List<Token> tokens = new ArrayList<>();
        for (String token : postfixExpression.split("")) {
            if (isOperator(token)) {
                tokens.add(Operator.get(token));
            } else {
                tokens.add(new Operand(token));
            }
        }

        return tokens;
    }

    public double evaluate() throws DivideByZeroException {
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
        return Double.parseDouble(stack.pop().getValue());
    }

    private Token perform(String operator, String operand1, String operand2) throws DivideByZeroException {
        double result = 0;
        if (operator.equals("+"))
            result = calculator.add(parseDouble(operand2), parseDouble(operand1));
        if (operator.equals("-"))
            result = calculator.subtract(parseDouble(operand2), parseDouble(operand1));
        if (operator.equals("*"))
            result = calculator.multiply(parseDouble(operand2), parseDouble(operand1));
        if (operator.equals("/"))
            result = calculator.divide(parseDouble(operand2), parseDouble(operand1));

        return new Operand(Double.toString(result));
    }

    private boolean isOperator(String token) {
        return token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/");
    }
}
