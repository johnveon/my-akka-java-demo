package com.example.my.akka.java.demo.helloworld;

import akka.actor.UntypedActor;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 15:00
 **/
public class GreeterActor extends UntypedActor {
    /**
     *
     */
    public enum Msg {
        GREET,
        DONE,
        ;
    }

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            Thread.sleep(1000);
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(msg);
        }
    }

}
