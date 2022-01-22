package com.example.springdemoall.aop.advised;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectJProxyFactoryDemoB {

    @Test
    public void test() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new HelloImpl());
        factory.addAspect(MyAspect.class);
        Hello proxy = (Hello) factory.getProxy();
        proxy.hello();
    }

    @Aspect
    public static class MyAspect {

        @Pointcut("execution(* com.example.springdemoall.aop.advised..hello())")
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

    public class HelloImpl implements Hello {

        @Override
        public void hello() {
            System.out.println("hello");
        }
    }
}
