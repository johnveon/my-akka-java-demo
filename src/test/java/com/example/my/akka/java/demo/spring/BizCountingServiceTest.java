package com.example.my.akka.java.demo.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BizCountingServiceTest {

    @Autowired
    private BizCountingService bizCountingService;

    @Test
    void count() throws Exception {

        bizCountingService.count();
    }
}