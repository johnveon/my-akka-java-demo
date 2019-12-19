package com.example.my.akka.java.demo.helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @Description: 主函数
 * @Author: zhoufengen
 * @Create At: 2019-12-17 15:02
 **/
public class HelloWorldMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef actorRef = system.actorOf(Props.create(HelloWorldActor.class), "helloWorld");
        System.out.println(actorRef.path());
    }
}
