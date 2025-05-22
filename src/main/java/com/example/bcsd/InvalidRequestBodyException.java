package com.example.bcsd;

public class InvalidRequestBodyException extends RuntimeException {
    private final int statusCode = 400;

    public InvalidRequestBodyException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
