package com.example.springdemo.scheduler;

import com.example.springdemo.exception.BadRequestException;
import com.example.springdemo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestSchedule {

    private final TestRepository testRepository;

    @Scheduled(fixedDelay = 1000 * 60)
    public void deleteTest() {
        try {
            testRepository.deleteById(5L);
        } catch (Exception ex) {
            throw new BadRequestException("not found for " + 5);
        }
    }
}
