package com.example.my.akka.java.demo.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
class ApplicationConfiguration {

    @Bean(destroyMethod = "shutdown")
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("actorSystem", akkaConfiguration());
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}