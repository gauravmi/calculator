package com.tw.expression;

import com.tw.Operand;
import com.tw.Token;
import com.tw.parser.ExpressionParser;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class InfixExpression {
    Stack<Token> operandStack;
    private final ExpressionParser expressionParser;
    private final String infixExpression;

    public InfixExpression(String expression, ExpressionParser expressionParser) {
        this.infixExpression = expression;
        operandStack = new Stack<>();
        this.expressionParser = expressionParser;
    }

    public String toPostfix() {
        List<Token> tokens = expressionParser.parse(infixExpression);
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
                handleClosingBracket(operatorStack);
            }

            else  { //operator case
                handleOperator(operatorStack, token);
            }
        });

        handleInvalidExpressions(operatorStack);

        return operandStack.stream().map(Token::getValue).collect(Collectors.joining(""));
    }

    private void handleInvalidExpressions(Stack<Token> operatorStack) {
        while (!operatorStack.isEmpty()) {
            if (Objects.equals(operatorStack.peek().getValue(), "(")) {
                throw new RuntimeException("Invalid expression");
            }
            Token pop = operatorStack.pop();
            operandStack.push(pop);
        }
    }

    private void handleOperator(Stack<Token> operatorStack, Token token) {
        while (!operatorStack.isEmpty() && token.precedence() <= operatorStack.peek().precedence()) {
            Token pop = operatorStack.pop();
            operandStack.push(pop);
        }
        operatorStack.push(token);
    }

    private void handleClosingBracket(Stack<Token> operatorStack) {
        while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek().getValue(), "(")) {
            Token operator = operatorStack.pop();
            operandStack.push(operator);
        }
        System.out.println("operatorStack = " + operatorStack);
        operatorStack.pop();
    }
}
