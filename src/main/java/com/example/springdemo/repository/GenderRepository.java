package com.example.springdemo.repository;

import com.example.springdemo.entity.GenderEntity;
import com.example.springdemo.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity,Long> {
}
