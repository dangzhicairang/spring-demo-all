package com.example.springdemoall.proxyprocessor.autoproxy;

import org.junit.jupiter.api.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanNameAutoProxyCreatorDemo {

    @Configuration
    public static class Config {

        @Bean
        public AbstractAutoProxyCreator autoProxyCreator() {

            BeanNameAutoProxyCreator beanNameAutoProxyCreator
                    = new BeanNameAutoProxyCreator();
            beanNameAutoProxyCreator.setBeanNames("*Impl");
            beanNameAutoProxyCreator.setInterceptorNames("advice");
            return beanNameAutoProxyCreator;
        }

        @Bean
        public MethodBeforeAdvice advice() {

            return (m, t, a) -> System.out.println("before");
        }
    }

    public interface Hello {
        void hello();
        void hello1();
    }

    @Component
    public static class HelloImpl implements Hello {

        @Override
        public void hello() {
            System.out.println("hello");
        }

        @Override
        public void hello1() {
            System.out.println("hello1");
        }
    }
    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(BeanNameAutoProxyCreatorDemo.class);
        Hello bean = context.getBean(Hello.class);

        /**
         * 因为声明的是 MethodBeforeAdvice，会被适配成 DefaultPointcutAdvisor
         *      即 MethodMatcher == TRUE，所以匹配 beanName 的目标 bean 的方法
         *      都会被代理
         */
        bean.hello();
        bean.hello1();
    }
}
