package com.tw.expression;

import com.tw.Operand;
import com.tw.parser.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tw.Operator.*;
import static com.tw.Operator.PLUS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InfixExpressionTest {
    private ExpressionParser expressionParser;

    @BeforeEach
    void beforeEach() {
        expressionParser = mock(ExpressionParser.class);
    }

    @Test
    void shouldConvertSimpleInfixExpression() {
        when(expressionParser.parse("1-1")).thenReturn(List.of(Operand.of("1"), MINUS, Operand.of("1")));
        String infixExpression = "1-1";
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression("11-", expressionParser)));
    }

    @Test
    void shouldConvertInfixExpressionWithoutBrackets() {
        when(expressionParser.parse("2+1*3+4")).thenReturn(List.of(operand("2"), PLUS, operand("1"), MULTIPLY, operand("3"), PLUS, operand("4")));
        String infixExpression = "2+1*3+4";
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression("213*+4+", expressionParser)));
    }

    @Test
    void shouldReturnConvertInfixExpressionWithBrackets() {
        when(expressionParser.parse("(8+9)*(3+4)")).thenReturn(List.of(operand("("), operand("8"), PLUS, operand("9"), operand(")"),
                MULTIPLY, operand("("), operand("3"), PLUS, operand("4"), operand(")")));
        String infixExpression = "(8+9)*(3+4)";
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression("89+34+*", expressionParser)));
    }

    @Test
    void shouldReturnConvertComplexInfixExpressionWithBrackets() {
        when(expressionParser.parse("((1+2)-3*(4/5))+6")).thenReturn(List.of(operand("("), operand("("), operand("1"), PLUS, operand("2"),
                operand(")"), MINUS, operand("3"), MULTIPLY, operand("("), operand("4"), DIV, operand("5"), operand(")"), operand(")"),
                PLUS, operand("6")));
        String infixExpression = "((1+2)-3*(4/5))+6";
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression("12+345/*-6+", expressionParser)));
    }

    private Operand operand(String value) {
        return Operand.of(value);
    }
}