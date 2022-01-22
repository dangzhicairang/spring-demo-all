package com.example.springdemoall.aop.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class AdvisorAdapterRegistryDemo {

    @Test
    public void test() {
        AdvisorAdapterRegistry registry = new DefaultAdvisorAdapterRegistry();
        MethodBeforeAdvice advice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before");
            }
        };

        // wrap advice to DefaultPointcutAdvisor
        Advisor wrap = registry.wrap(advice);

        // adapt advice to MethodInterceptor
        MethodInterceptor[] interceptors = registry.getInterceptors(new DefaultPointcutAdvisor(
                new MethodBeforeAdviceInterceptor(advice)
        ));
    }
}
