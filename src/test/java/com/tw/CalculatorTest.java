package com.tw;


import com.tw.exception.DivideByZeroException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("1+1");

        MatcherAssert.assertThat(result, is(2.0));
    }

    @Test
    void shouldSubtractTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("1-1");

        MatcherAssert.assertThat(result, is(0.0));
    }

    @Test
    void shouldHandleBoundaryConditionForMultiplication() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("1*0");

        MatcherAssert.assertThat(result, is(0.0));
    }

    @Test
    void shouldHandleNegativeResultForSubtraction() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("1-2");

        MatcherAssert.assertThat(result, is(-1.0));
    }

    @Test
    void shouldNotDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(DivideByZeroException.class, () -> calculator.calculate("1/0"));
    }
}