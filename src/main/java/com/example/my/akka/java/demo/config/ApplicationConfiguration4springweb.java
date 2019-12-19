package com.example.my.akka.java.demo.config;

import akka.actor.ActorSystem;
import com.example.my.akka.java.demo.springweb.demo.di.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApplicationConfiguration4springweb {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean(destroyMethod = "shutdown")
    public ActorSystem actorSystem4sw() {
        ActorSystem actorSystem = ActorSystem.create("demo-actor-system", akkaConfiguration4sw());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration4sw() {
        return ConfigFactory.load("application.conf");
    }
}