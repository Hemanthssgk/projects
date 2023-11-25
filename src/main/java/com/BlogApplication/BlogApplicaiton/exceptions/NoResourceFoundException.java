package com.BlogApplication.BlogApplicaiton.exceptions;

public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException(String resource, String fieldName, Integer fieldValue) {
        super(String.format("Resource %s with fieldName %s:%d",resource,fieldName,fieldValue));
    }

    public NoResourceFoundException(String message) {
        super(message);
    }
}
