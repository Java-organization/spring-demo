package com.example.springdemo.mapper;

import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.LoginResponse;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.entity.TestEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TestMapper {

    @Mapping(target = "fullName",expression = "java(testEntity.getName()+\" \"+testEntity.getSurname())")
    @Mapping(target = "gender",source = "gender.name")
    TestResponse toTestResponse(TestEntity testEntity);
    List<TestResponse> toTestResponses(List<TestEntity> testEntities);

    TestEntity toTestEntity(RequestDto dto);
    @Mapping(target = "gender",source = "gender.name")
    LoginResponse toLoginResponse(TestEntity testEntity);
}
