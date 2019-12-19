package com.example.my.akka.java.demo.unmodifiable;

/**
 * @Description: 主程序
 * @Author: zhoufengen
 * @Create At: 2019-12-17 16:30
 **/
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class HelloWorldMain {
//  public static void main(String[] args) {
//    akka.Main.main(new String[] { HelloWorldActor.class.getName() });
//  }

    public static void main(String[] args) {
        //创建ActorSystem。一般来说，一个系统只需要一个ActorSystem。
        //参数1：系统名称。参数2：配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("akka.config"));
        ActorRef a = system.actorOf(Props.create(HelloWorldActor.class), "helloWorld");
        System.out.println(a.path());
    }
}