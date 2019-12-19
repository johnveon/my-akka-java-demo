package com.example.my.akka.java.demo.future;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-18 09:58
 **/
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class PrintActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object o) {
        log.info("akka.future.PrintActor.onReceive:" + o);
        if (o instanceof Integer) {
            log.info("print:" + o);
        } else {
            unhandled(o);
        }
    }
}

