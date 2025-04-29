package com.ecommerce.project.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppGlobalHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> appMethodArgumentNotGivenException(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<String,String>();
        e.getBindingResult().getAllErrors().forEach((err)->{
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return  response;
    }

}
