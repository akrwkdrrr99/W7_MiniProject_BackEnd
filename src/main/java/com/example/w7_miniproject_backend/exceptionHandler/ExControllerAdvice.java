package com.example.todayerror.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        return new ErrorResult(HttpStatus.BAD_REQUEST, "401",  e.getMessage());
    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NullPointerException.class)
//    public ErrorResult NullExHandle(NullPointerException e) {
//        return new ErrorResult(HttpStatus.BAD_REQUEST, "402", e.getMessage());
//    }
}