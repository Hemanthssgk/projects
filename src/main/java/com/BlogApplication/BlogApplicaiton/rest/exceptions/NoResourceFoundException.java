package com.BlogApplication.BlogApplicaiton.rest.exceptions;

public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException(String resource, String fieldName, Integer fieldValue) {
        super(String.format("Resource %s with fieldName %s:%d",resource,fieldName,fieldValue));
    }

    public NoResourceFoundException(String message) {
        super(message);
    }
}
