package com.example.springdemoall.advised;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.InstantiationModelAwarePointcutAdvisor;
import org.springframework.aop.aspectj.annotation.AspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.MetadataAwareAspectInstanceFactory;
import org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.SimpleMetadataAwareAspectInstanceFactory;

import java.util.List;

public class AspectJProxyFactoryDemoA {

    @Test
    public void test() throws NoSuchMethodException {

        // MetadataAwareAspectInstanceFactory 来提供切面实例、切面元数据
        MetadataAwareAspectInstanceFactory aspectInstanceFactory
                = new SimpleMetadataAwareAspectInstanceFactory(MyAspect.class
                , "myAspect");

        // AspectJAdvisorFactory 基于此获取对应的 InstantiationModelAwarePointcutAdvisorImpl(s)
        AspectJAdvisorFactory aspectJAdvisorFactory
                = new ReflectiveAspectJAdvisorFactory();
        InstantiationModelAwarePointcutAdvisor advisor =
                (InstantiationModelAwarePointcutAdvisor) aspectJAdvisorFactory
                        .getAdvisor(
                                MyAspect.class.getMethod("hello")
                                , aspectInstanceFactory
                                , 0, "myAspect"
        );

        // 基于上述 InstantiationModelAwarePointcutAdvisorImpl(s) 创建代理
    }

    @Aspect
    public static class MyAspect {

        @Pointcut("execution(* *.hello())")
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
