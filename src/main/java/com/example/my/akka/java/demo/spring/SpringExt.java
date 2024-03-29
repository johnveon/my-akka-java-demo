package com.example.my.akka.java.demo.spring;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: spring ext
 * @Author: zhoufengen
 * @Create At: 2019-12-18 17:07
 **/
@Component("springExt")
public class SpringExt implements Extension, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Create a Props for the specified actorBeanName using the
     * SpringActorProducer class.
     *
     * @param actorBeanName
     *            The name of the actor bean to create Props for
     * @return a Props that will create the named actor bean using Spring
     */
    public Props props(String actorBeanName, Object ... args) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}