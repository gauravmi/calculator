package com.tw;

import com.tw.exception.DivideByZeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoValues() {
        double result = calculator.add(1.0, 1.0);

        assertThat(result, is(equalTo(2.0)));
    }

    @Test
    void shouldSubtractTwoValues() {
        double result = calculator.subtract(2.0, 1.0);

        assertThat(result, is(equalTo(1.0)));
    }

    @Test
    void shouldDivideTwoValues() throws DivideByZeroException {
        double result = calculator.divide(2.0, 2.0);

        assertThat(result, is(equalTo(1.0)));
    }

    @Test
    void shouldMultiplyTwoValues() {
        double result = calculator.multiply(2.0, 2.0);

        assertThat(result, is(equalTo(4.0)));
    }

    @Test
    void shouldNotDivideValueByZero() {
        assertThrows(DivideByZeroException.class, ()-> calculator.divide(1, 0));
    }
}