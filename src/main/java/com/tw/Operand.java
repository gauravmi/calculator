package com.tw;

public class Operand implements Token {
    private final String value;

    public Operand(String token) {
        value = token;
    }

    public static Operand of(String value) {
        return new Operand(value);
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

    // does not belong here; need refactoring
    @Override
    public int precedence() {return -11111;}

    @Override
    public String toString() {
        return "Operand{" +
                "value='" + value + '\'' +
                '}';
    }
}
