package com.example.springdemoall.aop.pointcut;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class PointCutDemo {

    @Deprecated
    public static class Sample {

        @Autowired
        public void a() {

        }

        // @After("")
        public void b() {

        }

    }

    @Test
    public void test() throws NoSuchMethodException {
        AnnotationMatchingPointcut annotationMatchingPointcut
                = new AnnotationMatchingPointcut(Deprecated.class, After.class, false);
        ComposablePointcut union = new ComposablePointcut(annotationMatchingPointcut)
                .union(new AnnotationMethodMatcher(Autowired.class));
        Method a = Sample.class.getMethod("a");
        Method b = Sample.class.getMethod("b");

        // true
        System.out.println(union.getMethodMatcher().matches(a, String.class));
        // false
        System.out.println(union.getMethodMatcher().matches(b, String.class));
    }
}
