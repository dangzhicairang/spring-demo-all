package com.example.springdemoall.beanfactory.applicationcontext;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AnnotationConfigApplicationContextDemo {

    public static class A {}

    @Configuration
    static class Config {

        @Bean
        public A a() {
            return new A();
        }
    }

    @Test
    public void test() {

        // 指定配置类就不用手动 refresh
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(A.class);
    }
}
