package com.tw.expression;

import com.tw.Operand;
import com.tw.Token;
import com.tw.exception.InvalidExpressionException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static com.tw.Operator.get;
import static java.lang.Double.parseDouble;

public class PostfixExpression {
    private final Stack<Token> stack;
    private final List<Token> tokensInPostfix;

    public PostfixExpression(List<Token> tokensInPostfix) {
        this.tokensInPostfix = tokensInPostfix;
        stack = new Stack<>();
    }

    public double evaluate() {

        try {
            for (Token token : tokensInPostfix) {
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
        } catch (EmptyStackException e) {
            throw new InvalidExpressionException("Invalid expression");
        }

        return resultUponSuccessfulEvaluationOfExpression();
    }

    private double resultUponSuccessfulEvaluationOfExpression() {
        if(stack.empty()) throw new InvalidExpressionException("Invalid expression");
        Token result = stack.pop();
        if(!stack.empty()) throw new InvalidExpressionException("Invalid expression");
        double resultDecimal;
        try {
            resultDecimal = parseDouble(result.getValue());
        } catch ( NumberFormatException e) { // last remaining result should be parsable as a Number
            throw new InvalidExpressionException("Invalid expression");
        }
        return resultDecimal;
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
        return Objects.equals(stack, that.stack) && Objects.equals(tokensInPostfix, that.tokensInPostfix);
    }

    @Override
    public String toString() {
        return "PostfixExpression{" +
                "stack=" + stack +
                ", tokensInPostfix=" + tokensInPostfix +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(stack, tokensInPostfix);
    }
}
