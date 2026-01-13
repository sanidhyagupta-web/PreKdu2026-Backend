package com.example.demo.Exceptions;

public class DuplicatePackageException extends RuntimeException{
    String message;
    DuplicatePackageException(String message){
        super(message);
        this.message = message;
    }
}