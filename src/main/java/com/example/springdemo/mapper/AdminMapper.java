package com.example.springdemo.mapper;

import com.example.springdemo.dto.response.LoginResponse;
import com.example.springdemo.entity.AdminEntity;
import com.example.springdemo.entity.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {
    LoginResponse toLoginResponse(AdminEntity adminEntity);
}
