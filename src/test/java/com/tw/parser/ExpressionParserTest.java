package com.tw.parser;

import com.tw.Operand;
import com.tw.Operator;
import com.tw.Token;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tw.Operand.of;
import static com.tw.Operator.*;

class ExpressionParserTest {
    @Test
    void shouldParseGivenExpressionWithAddition() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1+1"), Matchers.is(List.of(of("1"), PLUS, of("1"))));
    }

    @Test
    void shouldParseGivenExpressionWithSubtraction() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1-1"), Matchers.is(List.of(of("1"), MINUS, of("1"))));
    }

    @Test
    void shouldParseGivenExpressionWithDivision() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1/1"), Matchers.is(List.of(of("1"), DIV, of("1"))));
    }

    @Test
    void shouldParseGivenExpressionWithMultiplication() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1*1"), Matchers.is(List.of(of("1"), MULTIPLY, of("1"))));
    }

    @Test
    void shouldParseComplexExpression() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1*1-1+1/2"), Matchers.is(List.of(of("1"), MULTIPLY, of("1"), MINUS, of("1"), PLUS, of("1"), DIV, of("2"))));
    }

    @Test
    void shouldParseDecimalComplexExpression() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("1.1*1.3-1.3+1/2.0"), Matchers.is(List.of(of("1.1"), MULTIPLY, of("1.3"), MINUS, of("1.3"), PLUS, of("1"), DIV, of("2.0"))));
    }
}