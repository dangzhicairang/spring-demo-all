package com.example.springdemoall.beanfactory.event.applicationlistener;

import com.example.springdemoall.beanfactory.event.CustomEvent;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("com.example.springdemoall.beanfactory.event.applicationlistener");
        context.publishEvent(new CustomEvent(context, "default"));
    }
}
