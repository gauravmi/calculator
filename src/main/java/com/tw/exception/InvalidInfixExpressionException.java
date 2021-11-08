package com.tw.exception;

public class InvalidInfixExpressionException extends RuntimeException {
    public InvalidInfixExpressionException(String message) {
        super(message);
    }
}