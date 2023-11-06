package com.BlogApplication.BlogApplicaiton.exceptions;


import com.BlogApplication.BlogApplicaiton.exceptions.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(NoResourceFoundException noResourceFoundException)
    {
            return new ResponseEntity<>(new ApiResponse(noResourceFoundException.getMessage(),new Date(),false), HttpStatus.NOT_FOUND);
    }
}
