package com.example.springdemo.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {

    Long id;

    String token;

    String message;

    String name;

    String gmail;

    String surname;

    String gender;

    String phoneNumber;

    String filePath;
}
