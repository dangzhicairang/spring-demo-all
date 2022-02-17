package com.example.springdemoall.beanfactory.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

public class ScopeRegisterDemo {

    // @Scope 可作为 元注解 使用
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Scope("thread")
    public @interface ThreadScope {

        @AliasFor(annotation = Scope.class, attribute = "proxyMode")
        ScopedProxyMode proxyMode() default ScopedProxyMode.DEFAULT;
    }

    public static class ThreadBean {
        public ThreadBean() {
            System.out.println("ThreadBean init ...");
        }
        public void m() {}
    }

    public static class Main {

        ThreadBean threadBean;

        public Main(ThreadBean threadBean) {
            this.threadBean = threadBean;
        }

        // 由不同线程调用，则会创建新的作用域实例
        public void m() {
            new Thread(threadBean::m).start();
        }
    }

    @Configuration
    public static class Config {

        @Bean
        // 代理以注入单例实现预期语义
        @ThreadScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
        public ThreadBean threadBean() {
            return new ThreadBean();
        }

        @Bean
        public Main main() {
            return new Main(threadBean());
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();

        // ConfigurableBeanFactory#registerScope 注册作用域
        ConfigurableBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerScope("thread", new SimpleThreadScope());

        // 先注册在手动 refresh
        context.register(Config.class);
        context.refresh();

        // 达到预期语义
        Main bean = context.getBean(Main.class);
        bean.m();
        bean.m();
    }
}
