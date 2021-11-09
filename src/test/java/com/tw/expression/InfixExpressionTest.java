package com.tw.expression;

import com.tw.Operand;
import com.tw.Operator;
import com.tw.Token;
import com.tw.exception.InvalidInfixExpressionException;
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
        List<Token> tokens = List.of(Operand.of("1"), MINUS, Operand.of("1"));
        String infixExpression = "1-1";
        when(expressionParser.parse(infixExpression)).thenReturn(tokens);
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);
        List<Token> expectedTokens = List.of(Operand.of("1"), Operand.of("1"), MINUS);
        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression(expectedTokens)));
    }

    @Test
    void shouldConvertInfixExpressionWithoutBrackets() {
        List<Token> tokens = List.of(operand("2"), PLUS, operand("1"), MULTIPLY, operand("3"), PLUS, operand("4"));
        String infixExpression = "2+1*3+4";
        when(expressionParser.parse(infixExpression)).thenReturn(tokens);
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);
        List<Token> expectedTokens = List.of(operand("2"), operand("1"), operand("3"), MULTIPLY, PLUS, operand("4"), PLUS);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression(expectedTokens)));
    }

    @Test
    void shouldReturnConvertInfixExpressionWithBrackets() {
        List<Token> tokens = List.of(operand("8"), PLUS, operand("9"), MULTIPLY, operand("3"), PLUS, operand("4"));
        String infixExpression = "8+9*3+4";
        when(expressionParser.parse(infixExpression)).thenReturn(tokens);
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        List<Token> expectedTokens = List.of(operand("8"), operand("9"), operand("3"), MULTIPLY, PLUS, operand("4"), PLUS);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression(expectedTokens)));
    }

    @Test
    void shouldReturnConvertComplexInfixExpressionWithBrackets() {
        List<Token> tokens = List.of(operand("1"), PLUS, operand("2"), MINUS, operand("3"), MULTIPLY, operand("4"), DIV, operand("5"),PLUS, operand("6"));
        String infixExpression = "1+2-3*4/5+6";
        when(expressionParser.parse(infixExpression)).thenReturn(tokens);
        InfixExpression infixToPostfix = new InfixExpression(infixExpression, expressionParser);

        List<Token> expectedTokens = List.of(operand("1"), operand("2"), PLUS, operand("3"), operand("4"), MULTIPLY, operand("5"), DIV, MINUS, operand("6"), PLUS);

        assertThat(infixToPostfix.toPostfix(), is(new PostfixExpression(expectedTokens)));

    }

    private Operand operand(String value) {
        return Operand.of(value);
    }
}