package com.tw.parser;

import com.tw.Operator;
import com.tw.Token;

import java.util.ArrayList;
import java.util.List;

import static com.tw.Operand.of;
import static com.tw.Operator.get;

public class ExpressionParser {
    public List<Token> parse(String expression) {
        List<Token> tokens = new ArrayList<>();
        for (String token : expression.split("((?=\\+)|(?<=\\+))|((?=\\-)|(?<=\\-))|((?=\\/)|(?<=\\/))|((?=\\*)|(?<=\\*))")) {
            if (isOperator(token)) {
                tokens.add(get(token));
            } else { //  TODO: can be improved to add a logic to identify proper operands
                tokens.add(of(token));
            }
        }

        return tokens;
    }
    private boolean isOperator(String token) {
        return Operator.isOperator(token);
    }
}
