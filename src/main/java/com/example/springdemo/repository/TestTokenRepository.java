package com.example.springdemo.repository;

import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.entity.TestTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTokenRepository extends JpaRepository<TestTokenEntity,String> {
}
