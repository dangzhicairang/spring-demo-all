package com.example.springdemoall.aop.advised;

import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ProxyFactoryBeanDemo {

    @Configuration
    public static class Config {

        @Bean
        public MethodBeforeAdvice beforeAdvice() {

            return (m, t, a) -> System.out.println("before");
        }

        @Bean
        public Advisor advisor() {
            NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor
                    = new NameMatchMethodPointcutAdvisor(beforeAdvice());
            nameMatchMethodPointcutAdvisor.setMappedName("h*");
            return nameMatchMethodPointcutAdvisor;
        }

        @Bean
        public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
            ProxyFactoryBean factoryBean = new ProxyFactoryBean();
            factoryBean.setProxyInterfaces(new Class[]{ Hello.class });
            factoryBean.setInterceptorNames("advisor");
            factoryBean.setTarget(new HelloImpl());
            return factoryBean;
        }

    }

    public interface Hello {
        void hello();
    }

    public static class HelloImpl implements Hello {

        @Override
        public void hello() {
            System.out.println("hello");
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);
        Hello bean = context.getBean(Hello.class);
        bean.hello();
    }
}
