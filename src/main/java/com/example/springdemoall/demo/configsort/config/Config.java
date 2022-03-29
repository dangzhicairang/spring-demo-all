package com.example.springdemoall.demo.configsort.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public Config() {
        System.out.println("Config ...");
    }

    @Configuration
    static class ConfigStaticMember {

        public ConfigStaticMember() {
            System.out.println("ConfigStaticMember ...");
        }
    }

    @Configuration
    class ConfigInnerMember {

        public ConfigInnerMember() {
            System.out.println("ConfigInnerMember ...");
        }
    }
}
