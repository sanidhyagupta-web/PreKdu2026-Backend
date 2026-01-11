package com.example.demo.Exception;

import com.example.demo.Utilities.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        logger.error("Error Occurred : ResourceNotFoundException");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Error Occurred : MethodArgumentNotValidException");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ApiResponse> RunTimeExceptionHandler(DuplicateBookException ex){
        logger.error("Error Occurred : DuplicateBookException");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CONFLICT);
    }

}