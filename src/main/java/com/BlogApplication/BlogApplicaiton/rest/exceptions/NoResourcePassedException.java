package com.BlogApplication.BlogApplicaiton.rest.exceptions;

public class NoResourcePassedException extends RuntimeException {
    public NoResourcePassedException(String message) {
        super(message);
    }
}
