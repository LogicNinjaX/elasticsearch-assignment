package com.example.elasticsearch_assignment.exception;

public class InvalidRequestParameterException extends RuntimeException{
    public InvalidRequestParameterException() {
    }

    public InvalidRequestParameterException(String message) {
        super(message);
    }
}
