package com.tw.expression;

import com.tw.Operand;
import com.tw.Token;
import com.tw.parser.ExpressionParser;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static com.tw.Operator.get;
import static java.lang.Double.parseDouble;

public class PostfixExpression {
    private final Stack<Token> stack;
    private final String expression;
    private final ExpressionParser expressionParser;

    public PostfixExpression(String expression, ExpressionParser expressionParser) {
        this.expression = expression;
        this.expressionParser = expressionParser;
        stack = new Stack<>();
    }

    public double evaluate() {
        List<Token> tokens = expressionParser.parse(expression);
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
        return Operand.of(Double.toString(result));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostfixExpression that = (PostfixExpression) o;
        return Objects.equals(stack, that.stack) && Objects.equals(expression, that.expression) && Objects.equals(expressionParser, that.expressionParser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stack, expression, expressionParser);
    }
}
