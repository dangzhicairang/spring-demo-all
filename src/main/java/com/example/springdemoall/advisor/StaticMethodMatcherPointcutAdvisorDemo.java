package com.example.springdemoall.advisor;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

public class StaticMethodMatcherPointcutAdvisorDemo extends StaticMethodMatcherPointcutAdvisor {

    // 实现 match 逻辑
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return true;
    }

    @Test
    public void test() {
        StaticMethodMatcherPointcutAdvisorDemo demo = new StaticMethodMatcherPointcutAdvisorDemo();

        // 作为一个 Pointcut 可以暴露 ClassFilter 的能力
        demo.setClassFilter(clazz -> true);

        // 作为一个 PointcutAdvisor 可以暴露 Advice 的能力
        demo.setAdvice(new MethodBeforeAdviceInterceptor((
                (method, args, target) -> System.out.println("before"))));

        // MethodMatcher 的能力
        demo.matches(null, null);
    }
}
