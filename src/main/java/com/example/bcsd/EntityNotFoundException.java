package com.example.bcsd;

public class EntityNotFoundException extends RuntimeException {
    private final int statusCode = 404;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
