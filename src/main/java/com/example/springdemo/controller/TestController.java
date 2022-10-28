package com.example.springdemo.controller;

import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.logger.MainLogger;
import com.example.springdemo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/test")
@RequiredArgsConstructor
//@Slf4j
public class TestController {

    // Get Post Delete Put  PROTOCOL
    private final TestService testService;
    private final MainLogger log = MainLogger.getLogger(TestService.class);

    @GetMapping
    Page<TestResponse> getTests(int pageNumber,int pageSize) {
        return testService.getTests(PageRequest.of(pageNumber,pageSize));
    }

    @GetMapping("/{id}")
    ResponseEntity<TestResponse> getTest(@PathVariable Long id) {
        log.info("searching Test for id " + id);
        TestResponse response = testService.getTest(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    ResponseEntity<Void> saveTest(@Valid RequestDto requestDto) {
        testService.saveTest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        log.warn("deleted Test for id {}", id);
        testService.deleteTest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping()
    TestResponse updateTest(@RequestBody RequestDto requestDto) {
       return testService.updateTest(requestDto);

    }

    @PatchMapping("/{id}")
    TestResponse updatePhone(@PathVariable Long id, @RequestBody String phoneNumber) {
        return testService.updatePhone(id, phoneNumber);
    }
}
