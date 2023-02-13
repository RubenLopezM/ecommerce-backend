package com.pruebaback.prueba.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class CustomError {

    private int httpCode;
    private String message;
    private String type;
    private Date timestamp;
}
