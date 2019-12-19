package com.example.my.akka.java.demo.persistence;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.alibaba.fastjson.JSON;

/**
 * @Description:
 * @Author: zhoufengen
 * @Create At: 2019-12-18 14:22
 **/
public class EventHandler extends UntypedActor {


    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object msg ) throws Exception {
        log.info( "Handled Event: " + JSON.toJSONString(msg));
    }
}
