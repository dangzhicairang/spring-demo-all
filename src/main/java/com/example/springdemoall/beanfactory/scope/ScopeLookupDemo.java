package com.example.springdemoall.beanfactory.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
public class ScopeLookupDemo {

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class B {
        public B() {
            System.out.println("init ...");
        }
    }

    @Component
    public static class A {

        @Lookup
        public B b() {
            return null;
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ScopeLookupDemo.class);
        A bean = context.getBean(A.class);
        System.out.println(bean.b());
        System.out.println(bean.b());
    }
}
