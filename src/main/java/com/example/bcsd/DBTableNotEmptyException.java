package com.example.bcsd;

public class DBTableNotEmptyException extends RuntimeException {
    private final int statusCode = 400;

    public DBTableNotEmptyException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
