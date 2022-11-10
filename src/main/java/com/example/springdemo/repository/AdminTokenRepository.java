package com.example.springdemo.repository;

import com.example.springdemo.entity.AdminTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminTokenRepository extends JpaRepository<AdminTokenEntity,String> {
}
