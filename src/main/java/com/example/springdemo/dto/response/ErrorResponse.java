package com.example.springdemo.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private Map<String, String> checks;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
