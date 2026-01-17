package com.example.demo.Exception;

import com.example.demo.Utilities.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ApiResponse> RunTimeExceptionHandler(DuplicateBookException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CONFLICT);
    }

}