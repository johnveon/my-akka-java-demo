package com.example.my.akka.java.demo.springweb.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void perform(Object o) {
        logger.info("Perform: {}", o);
    }
}
