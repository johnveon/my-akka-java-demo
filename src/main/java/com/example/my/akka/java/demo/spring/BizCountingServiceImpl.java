package com.example.my.akka.java.demo.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.util.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

/**
 * @Description: 实现
 * @Author: zhoufengen
 * @Create At: 2019-12-18 17:13
 **/
@Service
public class BizCountingServiceImpl implements BizCountingService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExt springExt;

    @Override
    public String count() throws Exception {

        ActorRef counter = actorSystem.actorOf(springExt.props("CountingActor"), "counter");

// Create the "actor-in-a-box"
        final Inbox inbox = Inbox.create(actorSystem);

// tell it to count three times
        inbox.send(counter, new CountingActor.Count());
        inbox.send(counter, new CountingActor.Count());
        inbox.send(counter, new CountingActor.Count());

// print the result
        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = ask(counter, new CountingActor.Get(), Timeout.durationToTimeout(duration));
        try {
            System.out.println("Got back " + Await.result(result, duration));
        } catch (Exception e) {
            System.err.println("Failed getting result: " + e.getMessage());
            throw e;
        }
        return null;
    }
}
