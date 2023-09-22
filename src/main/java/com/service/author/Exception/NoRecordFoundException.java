package com.service.author.Exception;

public class NoRecordFoundException extends RuntimeException{
    String message;

    public NoRecordFoundException(String message) {
        super(message);
    }
}
