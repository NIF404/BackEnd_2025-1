package com.example.bcsd;

public class DBInvalidCreationRequestException extends RuntimeException
{
    private final int statusCode = 400;

    public DBInvalidCreationRequestException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
