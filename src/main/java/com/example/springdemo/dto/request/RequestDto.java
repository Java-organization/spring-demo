package com.example.springdemo.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestDto {

    String message;

    @NotEmpty(message = "name must not be null or empty")
    String name;

    @Size(min = 3,message = "surname size must not be less than 3")
    @NotEmpty(message = "surname must not be null or empty")
    String surname;

    @Pattern(regexp = "([0-9])+",message = "phone number only contain number")
    String phoneNumber;

    @Pattern(regexp = "(.+)@(.+)",message = "gmail must be correct regex")
    String gmail;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8,message = "Minimum 8 character must be entered")
    String password;

    Long genderId;

    MultipartFile file;
}
