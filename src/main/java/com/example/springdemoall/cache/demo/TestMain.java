package com.example.springdemoall.cache.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("com.example.springdemoall.cache.demo");
        DemoHandler bean = context.getBean(DemoHandler.class);

        // 第一次查并插入缓存 core
        System.out.println(bean.query("a"));

        System.out.println("---------");

        // 命中缓存 core
        System.out.println(bean.query("a"));

        System.out.println("---------");

        // 清空缓存 core
        bean.add(new DemoHandler.User(4, "a"));

        System.out.println("---------");

        // 再查一次并缓存 core
        System.out.println(bean.query("a"));

        System.out.println("---------");

        // 清空缓存 default
        bean.add2(new DemoHandler.User(5, "a"));

        System.out.println("---------");

        // 命中缓存 core
        System.out.println(bean.query("a"));

        System.out.println("---------");

        // 清除不同的 key
        bean.add3(new DemoHandler.User(6, "a"));

        System.out.println("---------");

        // 命中缓存 core
        System.out.println(bean.query("a"));
    }
}
