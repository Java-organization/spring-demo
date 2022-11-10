package com.example.springdemo.repository;

import com.example.springdemo.entity.AdminEntity;
import com.example.springdemo.entity.TestEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    Optional<AdminEntity> findByPhoneNumber(String phoneNumber);
}
