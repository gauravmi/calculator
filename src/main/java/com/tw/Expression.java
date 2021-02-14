package com.tw;

import com.tw.exception.DivideByZeroException;

import java.util.Stack;

import static java.lang.Double.parseDouble;

public class Expression {
    private String operation;
    private Stack<String> stack;
    private Calculator calculator;

    public Expression(String postfixExpression) {
        this.operation = postfixExpression;
        calculator = new Calculator(); // TODO: use mock
        stack = new Stack<>();
    }

    public double evaluate() throws DivideByZeroException {
        String[] split = operation.split("");
        for (String token : split) {
            if (isOperand(token)) {
                stack.push(token);
            }
            if (isOperator(token)) {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String result = perform(token, operand1, operand2);
                stack.push(result);
            }
        }
        return Double.parseDouble(stack.pop());
    }

    private String perform(String operator, String operand1, String operand2) throws DivideByZeroException {
        double result = 0;
        if(operator.equals("+"))
            result = calculator.add(parseDouble(operand2), parseDouble(operand1));
        if(operator.equals("-"))
            result = calculator.subtract(parseDouble(operand2), parseDouble(operand1));
        if(operator.equals("*"))
            result = calculator.multiply(parseDouble(operand2), parseDouble(operand1));
        if(operator.equals("/"))
            result = calculator.divide(parseDouble(operand2), parseDouble(operand1));

        return Double.toString(result);
    }

    private boolean isOperand(String token) {
        return !isOperator(token); // TODO : make actual regex match for operand
    }

    private boolean isOperator(String token) {
        return token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/");
    }
}
