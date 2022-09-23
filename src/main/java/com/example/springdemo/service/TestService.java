package com.example.springdemo.service;

import com.example.springdemo.controller.TestController;
import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.entity.GenderEntity;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.exception.UniquePhoneNumber;
import com.example.springdemo.logger.MainLogger;
import com.example.springdemo.mapper.TestMapper;
import com.example.springdemo.repository.GenderRepository;
import com.example.springdemo.repository.TestRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestService {
    MainLogger log = MainLogger.getLogger(TestController.class);
    final TestRepository testRepository;

    final GenderRepository genderRepository;

    final TestMapper testMapper;

    public TestResponse getTest(Long id) {
        TestEntity testEntity = null;
        try {
            testEntity = testRepository.findById(id)
                    .orElseThrow(() -> new Exception("Test not found for " + id + " ID"));
        } catch (Exception ex) {
            log.error("Test not found for " + id + " ID");
        }
        return testMapper.toTestResponse(testEntity);
    }

    @SneakyThrows
    public void saveTest(RequestDto requestDto) {
        TestEntity testEntity=testMapper.toTestEntity(requestDto);
        if(requestDto.getGenderId()==1 || requestDto.getGenderId()==2){
           GenderEntity genderEntity= genderRepository.findById(requestDto.getGenderId()).get();
            testEntity.setGender(genderEntity);
        }
        boolean exists = testRepository.existsByPhoneNumber(requestDto.getPhoneNumber());
        if (exists) {
            throw new Exception("This phoneNumber already exists");
        }
        testRepository.save(testEntity);
    }

    @SneakyThrows
    public void deleteTest(Long id) {
        TestEntity dbEntity = testRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        testRepository.deleteById(id);
    }

    public List<TestResponse> getTests() {
        List<TestEntity> testEntities = testRepository.findAll();
        return testMapper.toTestResponses(testEntities);
    }

    @SneakyThrows
    public TestEntity updateTest(TestEntity testEntity) {
        TestEntity dbEntity = testRepository.findById(testEntity.getId())
                .orElseThrow(() -> new Exception("User not found"));
        dbEntity.setName(testEntity.getName());
        dbEntity.setMessage(testEntity.getMessage());
        return testRepository.save(dbEntity);
    }

    @SneakyThrows
    public TestEntity updatePhone(Long id, String phoneNumber) {
        TestEntity test = testRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        boolean exists = testRepository.existsByPhoneNumber(phoneNumber);
        if (exists) {
            throw new UniquePhoneNumber("This phone number already exists");
        }
        test.setPhoneNumber(phoneNumber);
        return testRepository.save(test);
    }
}
