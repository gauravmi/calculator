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
    void shouldNotDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(DivideByZeroException.class, () -> calculator.calculate("1/0"));
    }
}