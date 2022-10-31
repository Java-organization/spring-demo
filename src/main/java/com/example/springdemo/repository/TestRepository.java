package com.example.springdemo.repository;

import com.example.springdemo.entity.TestEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Long> { // generic

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<TestEntity> findByPhoneNumber(String phoneNumber);

    List<TestEntity> gmailContaining(String gmail);
}