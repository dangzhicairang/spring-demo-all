package com.example.springdemoall.beanfactory.event.eventlistener;

import com.example.springdemoall.beanfactory.event.CustomEvent;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("com.example.springdemoall.beanfactory.event.eventlistener");
        context.publishEvent(new CustomEvent(context, "default"));
        System.out.println("=======================");
        context.publishEvent(new CustomEvent(context, "other"));
    }
}
