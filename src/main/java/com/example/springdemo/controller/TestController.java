package com.example.springdemo.controller;

import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.logger.MainLogger;
import com.example.springdemo.service.TestService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/test")
@RequiredArgsConstructor
//@Slf4j
public class TestController {

    // Get Post Delete Put  PROTOCOL
    private final TestService testService;
    private MainLogger log=MainLogger.getLogger(TestService.class);

    @GetMapping
    List<TestResponse> getTests() {
        return testService.getTests();
    }

    @GetMapping("/{id}")
    TestResponse getTest(@PathVariable Long id) {
        log.info("searching Test for id "+id);
        return testService.getTest(id);
    }

    @PostMapping
    void saveTest(@Valid @RequestBody RequestDto requestDto)  {
        testService.saveTest(requestDto);
    }

    @DeleteMapping("/{id}")
    void deleteTest(@PathVariable Long id) {
        log.warn("deleted Test for id {}",id);
        testService.deleteTest(id);
    }

    @PutMapping()
    TestEntity updateTest(@RequestBody TestEntity testEntity)  {
       return testService.updateTest(testEntity);
    }

    @PatchMapping("/{id}")
    TestEntity updatePhone(@PathVariable Long id,@RequestBody String phoneNumber) {
        return testService.updatePhone(id,phoneNumber);
    }
}
