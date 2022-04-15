package com.example.todayerror.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {
    private HttpStatus httpStatus;
    private String code;
    private String message;
}