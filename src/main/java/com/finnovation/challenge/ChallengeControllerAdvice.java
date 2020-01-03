package com.finnovation.challenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ChallengeControllerAdvice {
    @ExceptionHandler(ChallengeException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Object processValidationError(ChallengeException ex) {
        String result = ex.getMessage();
        return ex;
    }
}
