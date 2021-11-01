package com.tw;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class InfixToPostfixTest {

    @Test
    void shouldConvertSimpleInfixExpression() {
        String infixExpression = "1-1";
        InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter(infixExpression);

        assertThat(infixToPostfix.convert(), is("11-"));
    }

    @Test
    void shouldConvertInfixExpressionWithoutBrackets() {
        String infixExpression = "2+1*3+4";
        InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter(infixExpression);

        assertThat(infixToPostfix.convert(), is("213*+4+"));
    }

    @Test
    void shouldReturnConvertInfixExpressionWithBrackets() {
        String infixExpression = "(8+9)*(3+4)";
        InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter(infixExpression);

        assertThat(infixToPostfix.convert(), is("89+34+*"));
    }

    @Test
    void shouldReturnConvertComplexInfixExpressionWithBrackets() {
        String infixExpression = "((1+2)-3*(4/5))+6";
        InfixToPostfixConverter infixToPostfix = new InfixToPostfixConverter(infixExpression);

        assertThat(infixToPostfix.convert(), is("12+345/*-6+"));
    }
}