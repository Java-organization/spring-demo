package com.example.springdemo.controller;

import com.example.springdemo.dto.request.RequestDto;
import com.example.springdemo.dto.response.TestResponse;
import com.example.springdemo.entity.TestEntity;
import com.example.springdemo.logger.MainLogger;
import com.example.springdemo.service.TestService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final MainLogger log = MainLogger.getLogger(TestService.class);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<TestResponse> getTests(int pageNumber,int pageSize) {
        return testService.getTests(PageRequest.of(pageNumber,pageSize));
    }

    @GetMapping("/{id}")
    ResponseEntity<TestResponse> getTest(@PathVariable Long id) {
        log.info("searching Test for id " + id);
        TestResponse response = testService.getTest(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/signup")
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
    TestResponse updateTest(@RequestBody TestEntity testEntity) {
        return testService.updateTest(testEntity);
    }

    @PatchMapping("/{id}")
    TestResponse updatePhone(@PathVariable Long id, @RequestBody String phoneNumber) {
        return testService.updatePhone(id, phoneNumber);
    }
}
