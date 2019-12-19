package com.example.my.akka.java.demo.unmodifiable;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 16:29
 **/
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

public class GreeterActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        try {
            System.out.println("Greeter收到的数据为：" + JSONObject.toJSONString(msg));
            //给发送至发送信息.
            getSender().tell("Greeter工作完成。", getSelf());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}