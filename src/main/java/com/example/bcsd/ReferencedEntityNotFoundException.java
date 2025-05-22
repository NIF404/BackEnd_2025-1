package com.example.bcsd;

public class ReferencedEntityNotFoundException extends RuntimeException {
    private final int statusCode = 400;

    public ReferencedEntityNotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
