package com.example.springdemo.service;

import com.example.springdemo.controller.TestController;
import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.entity.GenderEntity;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.exception.BadRequestException;
import com.example.springdemo.exception.NotFoundException;
import com.example.springdemo.exception.UniquePhoneNumber;
import com.example.springdemo.logger.MainLogger;
import com.example.springdemo.mapper.TestMapper;
import com.example.springdemo.repository.GenderRepository;
import com.example.springdemo.repository.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            testEntity = testRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Test not found for " + id + " ID"));

        return testMapper.toTestResponse(testEntity);
    }


    public void saveTest(RequestDto requestDto) {
        TestEntity testEntity=testMapper.toTestEntity(requestDto);
        if(requestDto.getGenderId()==1 || requestDto.getGenderId()==2){
           GenderEntity genderEntity= genderRepository.findById(requestDto.getGenderId()).get();
            testEntity.setGender(genderEntity);
        }
        else{
            throw new BadRequestException("Bad request for gender id");
        }
        boolean exists = testRepository.existsByPhoneNumber(requestDto.getPhoneNumber());
        if (exists) {
            throw new UniquePhoneNumber("This phoneNumber already exists");
        }
        testRepository.save(testEntity);
    }

    public void deleteTest(Long id) {
        TestEntity dbEntity = testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        testRepository.deleteById(id);
    }

    public Page<TestResponse> getTests(Pageable pageable) {
        Page<TestEntity> testEntities = testRepository.findAll(pageable);
        return testEntities.map(testMapper::toTestResponse);
    }

    public TestResponse updateTest(TestEntity testEntity) {
        TestEntity dbEntity = testRepository.findById(testEntity.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        dbEntity.setName(testEntity.getName());
        dbEntity.setMessage(testEntity.getMessage());
       testEntity=testRepository.save(dbEntity);
        return testMapper.toTestResponse(testEntity);
    }

    public TestResponse updatePhone(Long id, String phoneNumber) {
        TestEntity test = testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        boolean exists = testRepository.existsByPhoneNumber(phoneNumber);
        if (exists) {
            throw new UniquePhoneNumber("This phone number already exists");
        }
        test.setPhoneNumber(phoneNumber);
        TestEntity testEntity=testRepository.save(test);
        return testMapper.toTestResponse(testEntity);
    }
}
