package com.tw.expression;

import com.tw.Operand;
import com.tw.Token;
import com.tw.exception.InvalidInfixExpressionException;
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

    public PostfixExpression toPostfix() {
        List<Token> tokens = expressionParser.parse(infixExpression);
        Stack<Token> operatorStack = new Stack<>();
        tokens.forEach(token -> {
            boolean isDigit = token.isOperand() && !isOpenBracket(token) && !isCloseBracket(token);

            if (isDigit) {
                operandStack.push(token);
            }

            else if (isOpenBracket(token)) {
                operatorStack.push(Operand.of("("));
            }

            else if (isCloseBracket(token)) {
                handleClosingBracket(operatorStack);
            }

            else  { // operator case
                handleOperator(operatorStack, token);
            }
        });

        handleInvalidExpressions(operatorStack);

        String expression = operandStack.stream().map(Token::getValue).collect(Collectors.joining(""));
        return new PostfixExpression(expression, expressionParser);
    }

    private boolean isCloseBracket(Token token) {
        return Objects.equals(token.getValue(), ")");
    }

    private boolean isOpenBracket(Token token) {
        return Objects.equals(token.getValue(), "(");
    }

    private void handleInvalidExpressions(Stack<Token> operatorStack) {
        while (!operatorStack.isEmpty()) {
            if (isOpenBracket(operatorStack.peek())) {
                throw new InvalidInfixExpressionException("Invalid expression");
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
        while (!operatorStack.isEmpty() && !isOpenBracket(operatorStack.peek())) {
            Token operator = operatorStack.pop();
            operandStack.push(operator);
        }
        System.out.println("operatorStack = " + operatorStack);
        operatorStack.pop();
    }
}
