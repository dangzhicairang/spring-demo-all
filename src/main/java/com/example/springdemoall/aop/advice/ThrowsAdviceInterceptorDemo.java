package com.example.springdemoall.aop.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.expression.ExpressionException;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ThrowsAdviceInterceptorDemo {

    public static class ThrowsAdviceSample {

        public void afterThrowing(IndexOutOfBoundsException ex) {
            System.out.println("catch IndexOutOfBoundsException");
        }

        public void afterThrowing(ExpressionException ex) {
            System.out.println("catch ExpressionException");
        }

    }

    @Test
    public void test1() throws Throwable {

        ThrowsAdviceInterceptor throwsAdviceInterceptor = new ThrowsAdviceInterceptor(new ThrowsAdviceSample());
        throwsAdviceInterceptor.invoke(
                new MethodInvocation() {
                    @Override
                    public Method getMethod() {
                        return null;
                    }

                    @Override
                    public Object[] getArguments() {
                        return new Object[0];
                    }

                    @Override
                    public Object proceed() throws Throwable {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    @Override
                    public Object getThis() {
                        return null;
                    }

                    @Override
                    public AccessibleObject getStaticPart() {
                        return null;
                    }
                }
        );
    }

}
