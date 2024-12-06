package com.example.contract.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {


    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<StatusResponse> handleListingNotFoundException(ListingNotFoundException ex) {
        StatusResponse response = new StatusResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StatusResponse> handleUserNotFoundException(UserNotFoundException ex) {
        StatusResponse response = new StatusResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StatusResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        StatusResponse response = new StatusResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ListingValidationException.class)
    public ResponseEntity<String> handleListingValidationException(ListingValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public record StatusResponse(String message, HttpStatus httpStatus) {
    }

}


