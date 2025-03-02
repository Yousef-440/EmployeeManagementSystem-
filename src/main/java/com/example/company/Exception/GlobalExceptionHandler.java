package com.example.company.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<ErrorObject> handelDepartmentNotFound(DepartmentNotFound ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = formatter.format(new Date());
        errorObject.setTime(formattedDate);

        return ResponseEntity.ok(errorObject);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorObject> handelEmployeeNotFound(EmployeeNotFound ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = formatter.format(new Date());
        errorObject.setTime(formattedDate);

        return ResponseEntity.ok(errorObject);
    }
}
