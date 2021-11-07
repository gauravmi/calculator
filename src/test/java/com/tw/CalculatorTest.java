package com.tw;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;


class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("1+1");

        MatcherAssert.assertThat(result, is(2.0));
    }
}