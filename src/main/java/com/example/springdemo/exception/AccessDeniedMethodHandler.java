package com.example.springdemo.exception;

import com.example.springdemo.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedMethodHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ErrorResponse errorResponse=new ErrorResponse("Access denied");
        byte[] responseToSend=new ObjectMapper().writeValueAsString(errorResponse).getBytes(StandardCharsets.UTF_8);
        httpServletResponse.getOutputStream().write(responseToSend);
    }
}
