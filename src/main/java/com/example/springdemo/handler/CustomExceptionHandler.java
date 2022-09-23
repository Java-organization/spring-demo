package com.example.springdemo.handler;

import com.example.springdemo.dto.response.ErrorResponseDto;
import com.example.springdemo.dto.response.ErrorResponseValid;
import com.example.springdemo.exception.UniquePhoneNumber;
import com.example.springdemo.logger.MainLogger;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final MainLogger LOGGER = MainLogger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, String> checks = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            checks.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorResponseValid errorResponse = new ErrorResponseValid("Bad request", checks);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return getResponseEntity(ex, status, request);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {

        HttpStatus status;
        if (ex instanceof UniquePhoneNumber) {
            status = HttpStatus.ALREADY_REPORTED;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        LOGGER.error("{} : {}", ex.getClass(), ex.getMessage());
        return getResponseEntity(ex, status, request);
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex,
                                                     HttpStatus status,
                                                     WebRequest request) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(status.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, status);
    }
}
