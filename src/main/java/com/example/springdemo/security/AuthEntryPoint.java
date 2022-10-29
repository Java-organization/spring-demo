package com.example.springdemo.security;

import com.example.springdemo.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse errorResponse=new ErrorResponse("Unauthorized");
        byte[] responseToSend=new ObjectMapper().writeValueAsString(errorResponse).getBytes(StandardCharsets.UTF_8);
        httpServletResponse.getOutputStream().write(responseToSend);
    }
}
