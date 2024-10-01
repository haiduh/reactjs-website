package com.github.group37.roadmap.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class OtpVerificationException extends RuntimeException {

    public OtpVerificationException(String message) {
        super(message);
    }

}