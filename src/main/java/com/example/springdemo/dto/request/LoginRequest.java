package com.example.springdemo.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "Phone number must not be empty")
    String phoneNumber;

    @NotBlank(message = "Password must not be empty")
    String password;

}
