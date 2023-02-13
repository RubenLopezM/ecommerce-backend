package com.pruebaback.prueba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptions {

    @ExceptionHandler(CartNotFoundException.class)
    public final ResponseEntity<CustomError> handleCartNotFoundException(CartNotFoundException exception){
        CustomError customError = new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString(), new Date());
        return new ResponseEntity<CustomError>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<CustomError> handleProductNotFoundException(ProductNotFoundException exception){
        CustomError customError = new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),new Date());
        return new ResponseEntity<CustomError>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
