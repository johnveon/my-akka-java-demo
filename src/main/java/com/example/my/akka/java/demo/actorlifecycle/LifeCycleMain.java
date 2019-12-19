package com.example.my.akka.java.demo.actorlifecycle;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 16:44
 **/
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class LifeCycleMain {

    public static void main(String[] args) {
        //创建ActorSystem。一般来说，一个系统只需要一个ActorSystem。
        //参数1：系统名称。参数2：配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("akka.config"));
        ActorRef myWork = system.actorOf(Props.create(MyWorkActor.class), "MyWorkActor");
        ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "WatchActor");

        myWork.tell(MyWorkActor.Msg.WORKING, ActorRef.noSender());
        myWork.tell(MyWorkActor.Msg.DONE, ActorRef.noSender());

        //中断myWork
        //myWork.tell(MyWorkActor.Msg.CLOSE, ActorRef.noSender());

        myWork.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
