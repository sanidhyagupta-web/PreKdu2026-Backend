package com.example.demo.Exception;

public class ResourceNotFoundException extends RuntimeException{
    String ResourceName;
    String FieldName;
    Integer Value;

    public ResourceNotFoundException(String ResourceName,String FieldName,Integer Value){
        super(ResourceName + " with Field Name " + FieldName + " with value : " + Value + " was not found ");
        this.ResourceName = ResourceName;
        this.FieldName = FieldName;
        this.Value = Value;
    }
}