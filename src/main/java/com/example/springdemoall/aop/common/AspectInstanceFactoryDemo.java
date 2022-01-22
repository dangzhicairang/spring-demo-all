package com.example.springdemoall.aop.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectMetadata;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectInstanceFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class AspectInstanceFactoryDemo {

    @Component
    @Aspect
    public static class MyAspect {

        @Pointcut("execution(* com.example.springdemoall..*.a())")
        public void pointcut() {

        }

    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MyAspect.class);
        BeanFactoryAspectInstanceFactory beanFactoryAspectInstanceFactory
                = new BeanFactoryAspectInstanceFactory(context, "aspectInstanceFactoryDemo.MyAspect");

        // 跟容器中的切面实例是同一个
        MyAspect aspectInstance
                = (MyAspect) beanFactoryAspectInstanceFactory.getAspectInstance();
        System.out.println(aspectInstance == context.getBean(MyAspect.class));

        // 可以解析 AspectMetadata
        AspectMetadata aspectMetadata
                = beanFactoryAspectInstanceFactory.getAspectMetadata();
    }
}
