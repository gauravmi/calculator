package com.tw;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoValues() {
        double result = calculator.add(1.0, 1.0);

        MatcherAssert.assertThat(result, Is.is(equalTo(2.0)));
    }

    @Test
    void shouldSubtractTwoValues() {
        double result = calculator.subtract(2.0, 1.0);

        MatcherAssert.assertThat(result, Is.is(equalTo(1.0)));
    }
}