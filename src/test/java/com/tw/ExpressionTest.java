package com.tw;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ExpressionTest {
    @Test
    void shouldEvaluateExpressionsContainingAddition() {
        Expression expression = new Expression("11+");

        assertThat(expression.evaluate(), is(2.0));
    }

    @Test
    void shouldEvaluateExpressionContainingSubtraction() {
        Expression expression = new Expression("11-");

        assertThat(expression.evaluate(), is(0.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingMultiplication() {
        Expression expression = new Expression("22*");

        assertThat(expression.evaluate(), is(4.0));
    }

    @Test
    void shouldEvaluateExpressionsContainingDivision() {
        Expression expression = new Expression("22/");

        assertThat(expression.evaluate(), is(1.0));
    }

    @Test
    void shouldEvaluateComplexExpressions() {
        Expression expression = new Expression("224*2/+1-");

        assertThat(expression.evaluate(), is(5.0));
    }
}