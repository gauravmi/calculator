package com.tw;

import com.tw.exception.DivideByZeroException;
import org.junit.jupiter.api.Test;

import static com.tw.Operator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperatorTest {
    @Test
    void shouldAddTwoValues() {
        double result = PLUS.perform(1.0, 1.0);

        assertThat(result, is(equalTo(2.0)));
    }

    @Test
    void shouldSubtractTwoValues() {
        double result = MINUS.perform(2.0, 1.0);

        assertThat(result, is(equalTo(1.0)));
    }

    @Test
    void shouldDivideTwoValues() {
        double result = DIV.perform(2.0, 2.0);

        assertThat(result, is(equalTo(1.0)));
    }

    @Test
    void shouldMultiplyTwoValues() {
        double result = MULTIPLY.perform(2.0, 2.0);

        assertThat(result, is(equalTo(4.0)));
    }

    @Test
    void shouldNotDivideValueByZero() {
        assertThrows(DivideByZeroException.class, () -> DIV.perform(1, 0));
    }
}