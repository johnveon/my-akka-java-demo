package com.example.my.akka.java.demo.springweb.demo.actor;

import akka.actor.UntypedActor;
import com.example.my.akka.java.demo.springweb.demo.model.Message;
import com.example.my.akka.java.demo.springweb.demo.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public WorkerActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        businessService.perform(this + " " + message);

        if (message instanceof Message) {
            Message param = (Message) message;
            Message message1 = new Message("回复："+param.getPayload(), param.getId());
            future.complete(message1);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }
}
