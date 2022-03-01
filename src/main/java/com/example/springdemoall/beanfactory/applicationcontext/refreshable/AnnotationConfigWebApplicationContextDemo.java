package com.example.springdemoall.beanfactory.applicationcontext.refreshable;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AnnotationConfigWebApplicationContextDemo {

    @AllArgsConstructor
    public static class A {
        public String name;
    }

    @Configuration
    static class Config1 {

        @Bean
        public A a() {
            return new A("1");
        }
    }

    @Configuration
    static class Config2 {

        @Bean
        public A a() {
            return new A("2");
        }
    }

    @Test
    public void test() {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();

        context.register(Config1.class);
        context.refresh();
        System.out.println(context.getBean(A.class).name);

        // 配置覆盖
        context.register(Config2.class);
        // 多次执行 refresh
        context.refresh();
        System.out.println(context.getBean(A.class).name);
    }
}
