package com.tw;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class InfixToPostfixConverter {
    Stack<Token> operandStack;
    private final Expression expression;
    private final String infixExpression;

    public InfixToPostfixConverter(String infixExpression) {
        this.infixExpression = infixExpression;
        operandStack = new Stack<>();
        expression = new Expression(infixExpression);
    }

    public String convert() {
        List<Token> tokens = expression.parse(infixExpression);
        Stack<Token> operatorStack = new Stack<>();
        tokens.forEach(token -> {
            boolean isDigit = token.isOperand() && !Objects.equals(token.getValue(), "(") && !Objects.equals(token.getValue(), ")");
            if (isDigit) {
                operandStack.push(token);
            }

            else if (Objects.equals(token.getValue(), "(")) {
                operatorStack.push(new Operand("("));
            }

            else if (Objects.equals(token.getValue(), ")")) {
                while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek().getValue(), "(")) {
                    Token operator = operatorStack.pop();
                    operandStack.push(operator);
                }
                System.out.println("operatorStack = " + operatorStack);
                operatorStack.pop();
            }

            else  { //operator case
                while (!operatorStack.isEmpty() && token.precedence() <= operatorStack.peek().precedence()) {
                    Token pop = operatorStack.pop();
                    operandStack.push(pop);
                }
                operatorStack.push(token);
            }
        });

        while (!operatorStack.isEmpty()) {
            if (Objects.equals(operatorStack.peek().getValue(), "(")) {
                throw new RuntimeException("Invalid expression");
            }
            Token pop = operatorStack.pop();
            operandStack.push(pop);
        }

        return operandStack.stream().map(Token::getValue).collect(Collectors.joining(""));
    }
}
