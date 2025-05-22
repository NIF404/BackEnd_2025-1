package com.example.bcsd;

public class EntityHasArticleException extends RuntimeException {
    private final int statusCode = 400;

    public EntityHasArticleException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
