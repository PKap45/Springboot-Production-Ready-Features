package com.springboot.prodreadyfeatures.Advices;

import com.springboot.prodreadyfeatures.Exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound resourceNotFound)
    {
        ApiError apiError = new ApiError(
                resourceNotFound.getLocalizedMessage(),HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleAllExceptions(Exception exception)
//    {
//        ApiError apiError = new ApiError(
//                exception.getMessage(),HttpStatus.BAD_REQUEST
//        );
//        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
//    }
}
