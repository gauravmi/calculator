package com.tw.parser;

import com.tw.Operand;
import com.tw.Operator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

class ExpressionParserTest {
    @Test
    void shouldParseGivenExpression() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("11+"), Matchers.is(List.of(Operand.of("1"), Operand.of("1"), Operator.PLUS)));
    }

    @Test
    void shouldNotParseEmptyExpression() {
        ExpressionParser expressionParser = new ExpressionParser();

        MatcherAssert.assertThat(expressionParser.parse("11+"), Matchers.is(List.of(Operand.of("1"), Operand.of("1"), Operator.PLUS)));
    }
}