package com.example.bcsd;

public class EmailAlreadyExistsException extends RuntimeException {
    private final int statusCode = 409;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
