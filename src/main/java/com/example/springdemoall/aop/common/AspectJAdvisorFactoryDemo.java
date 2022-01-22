package com.example.springdemoall.aop.common;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.InstantiationModelAwarePointcutAdvisor;
import org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.SimpleMetadataAwareAspectInstanceFactory;

import java.lang.reflect.Method;
import java.util.List;

public class AspectJAdvisorFactoryDemo {

    @Aspect
    public static class MyAspect {

        @Pointcut("execution(* *.*())")
        public void pointcut() {

        }

        @Before("pointcut()")
        public void before() {

        }
    }

    @Test
    public void test() throws NoSuchMethodException {
        ReflectiveAspectJAdvisorFactory aspectJAdvisorFactory = new ReflectiveAspectJAdvisorFactory();
        SimpleMetadataAwareAspectInstanceFactory aspectInstanceFactory
                = new SimpleMetadataAwareAspectInstanceFactory(MyAspect.class,
                "myAspect");
        Method m = MyAspect.class.getMethod("before");

        // getAdvisors 方法
        List<Advisor> advisors = aspectJAdvisorFactory.getAdvisors(aspectInstanceFactory);

        // getAdvisor 方法
        Advisor advisor = aspectJAdvisorFactory
                .getAdvisor(m
                        , aspectInstanceFactory, 0
                        , "myAspect");

        // 基于 InstantiationModelAwarePointcutAdvisor 获取 Advice
        // 实际还是委托 AspectJAdvisorFactory#getAdvice
        Advice byAdvisor = null;
        Advice byFactory = null;
        if (advisor instanceof InstantiationModelAwarePointcutAdvisor) {
            InstantiationModelAwarePointcutAdvisor instantiationModelAwarePointcutAdvisor
                    = (InstantiationModelAwarePointcutAdvisor) advisor;
            byAdvisor = instantiationModelAwarePointcutAdvisor.getAdvice();

            // 上述方法其实是基于此方法获取
            byFactory = aspectJAdvisorFactory.getAdvice(
                    m
                    , (AspectJExpressionPointcut) instantiationModelAwarePointcutAdvisor.getPointcut()
                    , aspectInstanceFactory
                    , 0, "myAspect"
            );
        }
    }
}
