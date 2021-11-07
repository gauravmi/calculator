package com.tw;
import com.tw.expression.PostfixExpression;
import com.tw.parser.ExpressionParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpressionTest {
    private ExpressionParser expressionParser;

    @BeforeEach
    void beforeEach() {
        expressionParser = mock(ExpressionParser.class);
    }

    @Test
    void shouldEvaluateExpressionsContainingAddition() {
        when(expressionParser.parse("11+")).thenReturn(List.of(Operand.of("1"), Operand.of("1"), Operator.PLUS));
        PostfixExpression expression = new PostfixExpression("11+", expressionParser);

        assertThat(expression.evaluate(), is(2.0));
    }

    @Test
    void shouldEvaluateExpressionContainingSubtraction() {
        when(expressionParser.parse("11-")).thenReturn(List.of(Operand.of("1"), Operand.of("1"), Operator.MINUS));
        PostfixExpression expression = new PostfixExpression("11-", expressionParser);

        assertThat(expression.evaluate(), is(0.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingMultiplication() {
        when(expressionParser.parse("22*")).thenReturn(List.of(Operand.of("2"), Operand.of("2"), Operator.MULTIPLY));
        PostfixExpression expression = new PostfixExpression("22*", expressionParser);

        assertThat(expression.evaluate(), is(4.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingDivision() {
        when(expressionParser.parse("22/")).thenReturn(List.of(Operand.of("2"), Operand.of("2"), Operator.DIV));
        PostfixExpression expression = new PostfixExpression("22/", expressionParser);

        assertThat(expression.evaluate(), is(1.0));
    }

    @Test
    void shouldEvaluateComplexExpressions() {
        when(expressionParser.parse("224*2/+1-")).thenReturn(List.of(Operand.of("2"), Operand.of("2"), Operand.of("4"), Operator.MULTIPLY,
                Operand.of("2"), Operator.DIV, Operator.PLUS, Operand.of("1"), Operator.MINUS));
        PostfixExpression expression = new PostfixExpression("224*2/+1-", expressionParser);

        assertThat(expression.evaluate(), is(5.0));
    }
}