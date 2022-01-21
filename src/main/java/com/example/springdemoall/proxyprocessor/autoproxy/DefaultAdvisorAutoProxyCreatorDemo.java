package com.example.springdemoall.proxyprocessor.autoproxy;

import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DefaultAdvisorAutoProxyCreatorDemo {

    @Configuration
    public static class Config {

        @Bean
        public AbstractAutoProxyCreator autoProxyCreator() {

            DefaultAdvisorAutoProxyCreator autoProxyCreator
                    = new DefaultAdvisorAutoProxyCreator();

            // 打开前缀匹配
            autoProxyCreator.setUsePrefix(true);

            // 指定前缀
            autoProxyCreator.setAdvisorBeanNamePrefix("test");

            return autoProxyCreator;
        }

        @Bean
        public MethodBeforeAdvice advice() {

            return (m, a, t) -> System.out.println("before");
        }

        @Bean
        public Advisor testAdvisor() {
            NameMatchMethodPointcutAdvisor advisor
                    = new NameMatchMethodPointcutAdvisor(advice());
            advisor.setMappedName("hello*");
            return advisor;
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
                = new AnnotationConfigApplicationContext(DefaultAdvisorAutoProxyCreatorDemo.class);
        Hello bean = context.getBean(Hello.class);
        bean.hello();
        bean.hello1();
    }
}
