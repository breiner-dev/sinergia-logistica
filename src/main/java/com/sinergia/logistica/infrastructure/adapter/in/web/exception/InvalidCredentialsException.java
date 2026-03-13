package com.sinergia.logistica.infrastructure.adapter.in.web.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
