package com.example.springdemoall.beanfactory.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QualifierDemo {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("com.example.springdemoall.beanfactory.qualifier");
        HelloService.Container1 bean = context.getBean(HelloService.Container1.class);
        System.out.println(bean.getHelloService());
    }
}
