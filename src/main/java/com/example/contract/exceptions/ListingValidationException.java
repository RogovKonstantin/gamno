package com.example.contract.exceptions;

public class ListingValidationException extends RuntimeException {
    private final String message;

    public ListingValidationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
