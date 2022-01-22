package com.example.springdemoall.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
public class SpringAopDemo {

    @Aspect
    @Component
    public static class MyAspect {

        @Pointcut("execution(* com.example.springdemoall.aop.aop..*.hello())")
        public void pointcut() {}

        @Before("pointcut()")
        public void before() {
            System.out.println("before");
        }
    }

    public interface Hello {
        void hello();
    }

    @Component
    public static class HelloImpl implements Hello {
        @Override
        public void hello() {
            System.out.println("hello");
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(SpringAopDemo.class);
        Hello bean = context.getBean(Hello.class);
        bean.hello();
    }
}
