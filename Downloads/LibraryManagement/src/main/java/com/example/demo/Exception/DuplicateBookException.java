package com.example.demo.Exception;

public class DuplicateBookException extends RuntimeException{
    String message;
    public DuplicateBookException(String message){
        super(message);
        this.message = message;
    }
}
