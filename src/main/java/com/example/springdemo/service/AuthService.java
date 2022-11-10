package com.example.springdemo.service;

import com.example.springdemo.dto.request.LoginRequest;
import com.example.springdemo.dto.response.LoginResponse;
import com.example.springdemo.entity.AdminEntity;
import com.example.springdemo.entity.AdminTokenEntity;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.entity.TestTokenEntity;
import com.example.springdemo.exception.AuthenticationException;
import com.example.springdemo.mapper.AdminMapper;
import com.example.springdemo.mapper.TestMapper;
import com.example.springdemo.repository.AdminRepository;
import com.example.springdemo.repository.AdminTokenRepository;
import com.example.springdemo.repository.TestRepository;
import com.example.springdemo.repository.TestTokenRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthService {
    TestTokenRepository testTokenRepository;

    AdminTokenRepository adminTokenRepository;

    PasswordEncoder passwordEncoder;

    TestRepository testRepository;

    AdminRepository adminRepository;

    TestMapper testMapper;

    AdminMapper adminMapper;

    public LoginResponse login(LoginRequest loginRequest) {
        TestEntity testEntity = testRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow(() -> new AuthenticationException("Incorrect phone number or password"));

        boolean auth = passwordEncoder.matches(loginRequest.getPassword(), testEntity.getPassword());
        if (!auth) {
            throw new AuthenticationException("Incorrect phone number or password");
        }

        String token = generateRandomToken();
        LoginResponse loginResponse = testMapper.toLoginResponse(testEntity);
        loginResponse.setToken(token);
        TestTokenEntity testTokenEntity=TestTokenEntity.builder()
                .token(token)
                .test(testEntity).build();
        testTokenRepository.save(testTokenEntity);
        return loginResponse;
    }
    public LoginResponse loginAdmin(LoginRequest loginRequest) {
        AdminEntity adminEntity = adminRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow(() -> new AuthenticationException("Incorrect phone number or password"));

        boolean auth = passwordEncoder.matches(loginRequest.getPassword(), adminEntity.getPassword());
        if (!auth) {
            throw new AuthenticationException("Incorrect phone number or password");
        }
        String token = generateRandomToken();
        LoginResponse loginResponse = adminMapper.toLoginResponse(adminEntity);
        loginResponse.setToken(token);
        AdminTokenEntity adminTokenEntity=AdminTokenEntity.builder()
                .token(token)
                .admin(adminEntity).build();
        adminTokenRepository.save(adminTokenEntity);
        return loginResponse;
    }
    private String generateRandomToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void logout(String token) {
        token = token.substring(7);// wqwqwqwqwq
       testTokenRepository.findById(token).ifPresent(testTokenRepository::delete);

       adminTokenRepository.findById(token).ifPresent(adminTokenRepository::delete);
    }
}
