package com.example.my.akka.java.demo.springweb.demo.controller;

import com.example.my.akka.java.demo.springweb.demo.model.Message;
import com.example.my.akka.java.demo.springweb.demo.service.CompletableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DeferredResultController {

    private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

    private AtomicLong id = new AtomicLong(0);

    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/async-non-blocking")
    public DeferredResult<Message> getAsyncNonBlocking() {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        long andIncrement = id.getAndIncrement();
        CompletableFuture<Message> future = completableFutureService.get("async-non-blocking" + andIncrement, andIncrement);
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
}
