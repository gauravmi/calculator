package com.tw.expression;

import com.tw.Operand;
import com.tw.Operator;
import com.tw.Token;
import com.tw.exception.InvalidExpressionException;
import com.tw.parser.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostfixExpressionTest {
    private ExpressionParser expressionParser;

    @BeforeEach
    void beforeEach() {
        expressionParser = mock(ExpressionParser.class);
    }

    @Test
    void shouldEvaluateExpressionsContainingAddition() {
        List<Token> tokens = List.of(Operand.of("1"), Operand.of("1"), Operator.PLUS);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThat(expression.evaluate(), is(2.0));
    }

    @Test
    void shouldEvaluateExpressionContainingSubtraction() {
        List<Token> tokens = List.of(Operand.of("1"), Operand.of("1"), Operator.MINUS);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThat(expression.evaluate(), is(0.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingMultiplication() {
        List<Token> tokens = List.of(Operand.of("2"), Operand.of("2"), Operator.MULTIPLY);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThat(expression.evaluate(), is(4.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingDivision() {
        List<Token> tokens = List.of(Operand.of("2"), Operand.of("2"), Operator.DIV);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThat(expression.evaluate(), is(1.0));
    }

    @Test
    void shouldEvaluateComplexExpressions() {
        List<Token> tokens = List.of(Operand.of("2"), Operand.of("2"), Operand.of("4"), Operator.MULTIPLY,
                Operand.of("2"), Operator.DIV, Operator.PLUS, Operand.of("1"), Operator.MINUS);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThat(expression.evaluate(), is(5.0));
    }

    @Test
    void shouldNotEvaluateInvalidExpressions() {
        List<Token> tokens = List.of(Operand.of("2"), Operator.MINUS);
        PostfixExpression expression = new PostfixExpression(tokens);

        assertThrows(InvalidExpressionException.class, expression::evaluate);
    }
}