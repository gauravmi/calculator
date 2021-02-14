package com.tw;

import java.util.HashMap;
import java.util.Map;

public enum Operator implements Token {
    PLUS("+"),
    MINUS("-"),
    DIV("/"),
    MULTIPLY("*");
    private final String value;

    private static final Map<String, Operator> operators;

    static {
        operators = new HashMap<>();
        for (Operator operator : Operator.values()) {
            operators.put(operator.value, operator);
        }
    }

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static Operator get(String op) {
        return operators.get(op);
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public boolean isOperand() {
        return false;
    }
}
