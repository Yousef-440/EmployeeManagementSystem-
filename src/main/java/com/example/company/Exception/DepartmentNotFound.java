package com.example.company.Exception;

public class DepartmentNotFound extends RuntimeException{

    public DepartmentNotFound(String message){
        super(message);
    }

}
