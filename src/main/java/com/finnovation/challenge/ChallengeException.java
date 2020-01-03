package com.finnovation.challenge;

import org.springframework.http.HttpStatus;

public class ChallengeException extends RuntimeException {
    private int errorCode;

    public ChallengeException(String message) {
        super(message);
        this.errorCode = HttpStatus.NOT_ACCEPTABLE.value();
    }
}
