package com.tw;

public interface Token {
    boolean isOperator();
    boolean isOperand();
    String getValue();
}
