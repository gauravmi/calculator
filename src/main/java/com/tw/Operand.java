package com.tw;

public class Operand implements Token {
    private final String value;

    public Operand(String token) {
        value = token;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public boolean isOperand() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }
}
