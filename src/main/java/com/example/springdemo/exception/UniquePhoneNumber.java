package com.example.springdemo.exception;

public class UniquePhoneNumber extends RuntimeException {
    public UniquePhoneNumber(String message) {
        super(message);

    }
}
