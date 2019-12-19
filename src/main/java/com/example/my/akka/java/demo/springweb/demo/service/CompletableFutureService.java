package com.example.my.akka.java.demo.springweb.demo.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.example.my.akka.java.demo.springweb.demo.di.SpringExtension;
import com.example.my.akka.java.demo.springweb.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem4sw;

    @Autowired
    private SpringExtension springExtension;

    public CompletableFuture<Message> get(String payload, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem4sw.actorOf(springExtension.props("workerActor", future), "worker-actor");
        workerActor.tell(new Message(payload, id), null);
        return future;
    }
}
