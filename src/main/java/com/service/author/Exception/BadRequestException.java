package com.service.author.Exception;

public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message) {
        super(message);
    }
}

