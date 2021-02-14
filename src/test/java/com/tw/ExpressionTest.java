package com.tw;

import com.tw.exception.DivideByZeroException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ExpressionTest {
    @Test
    void shouldEvaluateExpressionsContainingAddition() throws DivideByZeroException {
        Expression expression = new Expression("11+");

        assertThat(expression.evaluate(), is(2.0));
    }

    @Test
    void shouldEvaluateExpressionContainingSubtraction() throws DivideByZeroException {
        Expression expression = new Expression("11-");

        assertThat(expression.evaluate(), is(0.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingMultiplication() throws DivideByZeroException {
        Expression expression = new Expression("22*");

        assertThat(expression.evaluate(), is(4.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingDivision() throws DivideByZeroException {
        Expression expression = new Expression("22/");

        assertThat(expression.evaluate(), is(1.0));
    }

    @Test
    void shouldEvaluateComplexExpressions() throws DivideByZeroException {
        Expression expression = new Expression("224*2/+1-");

        assertThat(expression.evaluate(), is(5.0));
    }
}