package com.tw;


import com.tw.exception.DivideByZeroException;
import com.tw.exception.InvalidExpressionException;
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
    void shouldDoTheAdditionForMoreThanTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("2+3+3");

        MatcherAssert.assertThat(result, is(8.0));
    }

    @Test
    void shouldDoTheAdditionForMoreThanTwoDecimalNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("2.1+3+3.5");

        MatcherAssert.assertThat(result, is(8.6));
    }

    @Test
    void shouldDoTheAdditionAndSubtractionForMoreThanTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("2+3-3");

        MatcherAssert.assertThat(result, is(2.0));
    }

    @Test
    void shouldDoTheDivisionAndSubtractionForMoreThanTwoNumbersConsideringThePrecedence() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("5/2-3");

        MatcherAssert.assertThat(result, is(-0.5));
    }

    @Test
    void shouldNotDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(DivideByZeroException.class, () -> calculator.calculate("1/0"));
    }

    @Test
    void shouldNotSupportOperator() {
        Calculator calculator = new Calculator();
        assertThrows(InvalidExpressionException.class, () -> calculator.calculate("1^0"));
    }
}