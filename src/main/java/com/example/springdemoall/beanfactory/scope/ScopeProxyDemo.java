package com.example.springdemoall.beanfactory.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Configuration
public class ScopeProxyDemo {

    @Component
    @Scope(
            scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE
            , proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    public static class PortoTypeBean {

        private double random = Math.random();

        public void m() {
            System.out.println(random);
        }
    }

    @Component
    public static class Main {

        @Autowired
        PortoTypeBean portoTypeBean;

        public void portoTypeMethod() {
            portoTypeBean.m();
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ScopeProxyDemo.class);
        Main bean = context.getBean(Main.class);

        bean.portoTypeMethod();
        bean.portoTypeMethod();
    }

}
