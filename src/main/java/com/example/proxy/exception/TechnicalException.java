package com.example.proxy.exception;

public class TechnicalException extends CustomException {
    public TechnicalException(String code, String message) {
        super(code, message);
    }
}
