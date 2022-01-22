package com.example.springdemoall.aop.advised;

import org.junit.jupiter.api.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class ProxyFactoryDemo {

    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory(new HelloImpl());
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before");
            }
        });
        Hello proxy = ((Hello) proxyFactory.getProxy());
        proxy.hello();
    }

    public interface Hello {

        void hello();

    }

    public class HelloImpl implements Hello {

        @Override
        public void hello() {
            System.out.println("hello");
        }
    }
}
