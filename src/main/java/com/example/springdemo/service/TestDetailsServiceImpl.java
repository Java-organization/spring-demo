package com.example.springdemo.service;

import com.example.springdemo.constant.Role;
import com.example.springdemo.entity.AdminEntity;
import com.example.springdemo.entity.AdminTokenEntity;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.entity.TestTokenEntity;
import com.example.springdemo.repository.AdminTokenRepository;
import com.example.springdemo.repository.TestTokenRepository;
import com.example.springdemo.security.UserPrincipal;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TestDetailsServiceImpl implements UserDetailsService {

    TestTokenRepository testTokenRepository;

    AdminTokenRepository adminTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
       Optional<TestTokenEntity> optionalTokenEntity = testTokenRepository.findById(token);
       if(optionalTokenEntity.isPresent()){
           TestEntity testEntity=optionalTokenEntity.get().getTest();
           return  new UserPrincipal(testEntity, Role.ROLE_TEST);
       }
        Optional<AdminTokenEntity> optionalAdminEntity = adminTokenRepository.findById(token);
        if(optionalAdminEntity.isPresent()){
            AdminEntity adminEntity=optionalAdminEntity.get().getAdmin();
            return  new UserPrincipal(adminEntity, Role.ROLE_ADMIN);
        }
        return null;
    }
}
