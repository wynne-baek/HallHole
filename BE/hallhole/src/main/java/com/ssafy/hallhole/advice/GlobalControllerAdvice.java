package com.ssafy.hallhole.advice;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFount(final NotFoundException e) {
        final ErrorResponse response = new ErrorResponse(e.getMessage(), e.getStatus());
        return response;
    }
}