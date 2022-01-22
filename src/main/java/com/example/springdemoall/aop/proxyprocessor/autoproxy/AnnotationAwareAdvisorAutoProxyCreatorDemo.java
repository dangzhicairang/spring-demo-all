package com.example.springdemoall.aop.proxyprocessor.autoproxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AnnotationAwareAdvisorAutoProxyCreatorDemo {

    @Configuration
    public static class Config {

        @Bean
        public AbstractAutoProxyCreator autoProxyCreator() {

            return new AnnotationAwareAspectJAutoProxyCreator();
        }
    }

    @Aspect
    @Component
    public static class MyAspect {

        @Pointcut("execution(* com.example.springdemoall.aop.proxyprocessor.autoproxy..*.hello())")
        public void pointcut() {

        }

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
                = new AnnotationConfigApplicationContext(AnnotationAwareAdvisorAutoProxyCreatorDemo.class);
        Hello bean = context.getBean(Hello.class);
        bean.hello();
    }
}
