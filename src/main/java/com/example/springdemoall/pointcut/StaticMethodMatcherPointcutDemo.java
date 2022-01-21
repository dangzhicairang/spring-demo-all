package com.example.springdemoall.pointcut;

import org.junit.jupiter.api.Test;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class StaticMethodMatcherPointcutDemo extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "a".equals(method.getName());
    }

    public void a() {

    }

    public void b() {

    }

    @Test
    public void test() throws NoSuchMethodException {
        StaticMethodMatcherPointcutDemo demo = new StaticMethodMatcherPointcutDemo();
        demo.setClassFilter(clazz -> clazz == StaticMethodMatcherPointcutDemo.class);
        boolean a = demo.matches(StaticMethodMatcherPointcutDemo.class.getMethod("a")
                , StaticMethodMatcherPointcutDemo.class);
        System.out.println(a);  // true
        boolean b = demo.matches(StaticMethodMatcherPointcutDemo.class.getMethod("b")
                , StaticMethodMatcherPointcutDemo.class);
        System.out.println(b);  // false
    }
}
