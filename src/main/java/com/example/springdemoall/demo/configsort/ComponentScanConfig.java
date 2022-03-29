package com.example.springdemoall.demo.configsort;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.springdemoall.demo.configsort.config")
public class ComponentScanConfig {

    public ComponentScanConfig() {
        System.out.println("ComponentScanConfig ...");
    }

    @Configuration
    static class ComponentScanStaticMember {

        public ComponentScanStaticMember() {
            System.out.println("ComponentScanStaticMember ...");
        }
    }

    @Configuration
    class ComponentScanTInnerMember {

        public ComponentScanTInnerMember() {
            System.out.println("ComponentScanInnerMember ...");
        }
    }
}
