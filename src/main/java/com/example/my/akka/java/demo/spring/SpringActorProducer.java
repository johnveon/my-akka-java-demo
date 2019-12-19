package com.example.my.akka.java.demo.spring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @Description: producer
 * @Author: zhoufengen
 * @Create At: 2019-12-18 17:07
 **/
public class SpringActorProducer implements IndirectActorProducer {
    private final ApplicationContext applicationContext;
    private final String actorBeanName;
    private final Object[] args;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName, Object ... args) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
        this.args = args;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName, args);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}