package com.example.my.akka.java.demo.future;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-18 09:57
 **/
import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class WorkerActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("akka.future.WorkerActor.onReceive:" + o);

        if (o instanceof Integer) {
            Thread.sleep(1000);
            int i = Integer.parseInt(o.toString());
            getSender().tell(i*i, getSelf());
        } else {
            unhandled(o);
        }
    }
}
