package com.example.springdemo.exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException() {
        super("Unauthorized");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
