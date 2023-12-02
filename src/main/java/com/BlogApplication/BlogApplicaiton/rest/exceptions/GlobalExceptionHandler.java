package com.BlogApplication.BlogApplicaiton.rest.exceptions;


import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * This class will only handle the exceptions thrown in controller. If there is an exception thrown in
 * filter or any other place. it will not be invoked. read below link to understand more. there are many ways
 * to handle exception in filter, one easy way is to handle exception in servlet level. ash shown in jwt validation filter class.
 * https://stackoverflow.com/questions/34595605/how-to-manage-exceptions-thrown-in-filters-in-spring
 */
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> accessDenied(AccessDeniedException accessDeniedException)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("Access Denied!!!",new Date(),false));
    }

}
