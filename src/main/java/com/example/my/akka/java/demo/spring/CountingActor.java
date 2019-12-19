package com.example.my.akka.java.demo.spring;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-18 17:11
 **/
@Component("CountingActor")
@Scope("prototype")
public class CountingActor extends UntypedActor {

    public static class Count {
    }

    public static class Get {
    }

    // the service that will be automatically injected
    @Resource
    private CountingService countingService;

    private int count = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Count) {
            count = countingService.increment(count);
        } else if (message instanceof Get) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }
}