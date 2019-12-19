package com.example.my.akka.java.demo.unmodifiable;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 16:28
 **/
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class HelloWorldActor extends UntypedActor {

    ActorRef greeter;

    @Override
    public void preStart() {
        // create the greeter actor
        greeter = getContext().actorOf(Props.create(GreeterActor.class), "greeter");
        System.out.println("GreeterActor actor path：" + greeter.path());
        // tell it to perform the greeting
        greeter.tell(new Message(2, Arrays.asList("2", "dsf")), getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        try {
            System.out.println("HelloWorld收到的数据为：" + JSONObject.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
