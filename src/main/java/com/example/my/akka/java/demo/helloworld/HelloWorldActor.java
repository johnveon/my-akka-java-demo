package com.example.my.akka.java.demo.helloworld;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 14:58
 **/
public class HelloWorldActor extends UntypedActor {

    @Override
    public void preStart() {
        // create the greeter actor
        final ActorRef greeterActorRef = getContext().actorOf(Props.create(GreeterActor.class), "greeter");
        // tell it to perform the greeting
        greeterActorRef.tell(GreeterActor.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == GreeterActor.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }


}
