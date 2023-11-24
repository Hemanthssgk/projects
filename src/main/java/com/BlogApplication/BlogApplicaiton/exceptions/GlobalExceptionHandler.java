package com.BlogApplication.BlogApplicaiton.exceptions;


import com.BlogApplication.BlogApplicaiton.exceptions.model.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(NoResourceFoundException noResourceFoundException)
    {
            return new ResponseEntity<>(new ApiResponse(noResourceFoundException.getMessage(),new Date(),false), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoResourcePassedException.class)
    public ResponseEntity<ApiResponse> resourceNotPassesExceptionHandler(NoResourcePassedException noResourcePassedException)
    {
            return new ResponseEntity<>(new ApiResponse(noResourcePassedException.getMessage(),new Date(),false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> validationExceptionHandler(ConstraintViolationException constraintViolationException)
    {
        Map<String,String> response = new HashMap<>();
        constraintViolationException.getConstraintViolations().forEach(errors -> {
            response.put(errors.getPropertyPath().toString(), errors.getMessage());
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> ioExceptionHandler(IOException ioException)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error occurred while uploading image!!!");
    }
}
