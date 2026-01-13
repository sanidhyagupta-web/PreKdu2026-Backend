package com.example.demo.Exceptions;


import com.example.demo.Utilities.ApiResponse;
import jakarta.validation.UnexpectedTypeException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Error Occurred : MethodArgumentNotValidException");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatePackageException.class)
    public ResponseEntity<ApiResponse> DuplicateExceptionHandler(DuplicatePackageException ex){
        logger.error("Error Occurred : DuplicateBookException");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ApiResponse> InvalidCredentialsHandler(InvalidCredentials ex){
        logger.error("Error Occured : InvalidCredentialsHandler");
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,"False");
        return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
    }

}
