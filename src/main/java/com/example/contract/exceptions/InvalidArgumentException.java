package com.example.contract.exceptions;


public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super("Invalid request: " + message);
    }
}

