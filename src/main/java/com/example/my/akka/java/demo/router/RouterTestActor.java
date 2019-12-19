package com.example.my.akka.java.demo.router;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-17 17:04
 **/
import akka.actor.*;
import akka.routing.*;
import com.example.my.akka.java.demo.mailbox.InboxTestActor;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class RouterTestActor extends UntypedActor {

    public Router router;

    {
        ArrayList<Routee> routees = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            //借用上面的 inboxActor
            ActorRef worker = getContext().actorOf(Props.create(InboxTestActor.class), "worker_" + i);
            getContext().watch(worker);//监听
            routees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object o) {
        if(o instanceof InboxTestActor.Msg){
            //进行路由转发
            router.route(o, getSender());
        }else if(o instanceof Terminated){
            //发生中断，将该actor删除。当然这里可以参考之前的actor重启策略，进行优化，为了简单，这里仅进行删除处理
            router = router.removeRoutee(((Terminated)o).actor());
            System.out.println(((Terminated)o).actor().path() + " 该actor已经删除。router.size=" + router.routees().size());

            //没有可用actor了
            if(router.routees().size() == 0){
                System.out.print("没有可用actor了，系统关闭。");
                flag.compareAndSet(true, false);
                getContext().system().shutdown();
            }
        }else {
            unhandled(o);
        }

    }


    public  static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterTestActor.class), "RouterTestActor");

        int i = 1;
        while(flag.get()){
            routerTest.tell(InboxTestActor.Msg.WORKING, ActorRef.noSender());

            if(i % 10 == 0){
                routerTest.tell(InboxTestActor.Msg.CLOSE, ActorRef.noSender());
            }

            Thread.sleep(500);

            i ++;
        }
    }
}
