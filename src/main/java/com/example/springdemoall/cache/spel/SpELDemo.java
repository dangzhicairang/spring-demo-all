package com.example.springdemoall.cache.spel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo {

    @Configuration
    public static class Config {

        @Bean
        public A a() {
            return new A("bean");
        }
    }

    public static class A {

        private String name;

        public A(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() {
        ConfigurableListableBeanFactory beanFactory
                = new AnnotationConfigApplicationContext(Config.class)
                .getBeanFactory();

        StandardEvaluationContext context = new StandardEvaluationContext();
        // 指定 BeanResolver
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        // 指定 Variable
        context.setVariable("v", "test");

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("@a");
        A a = expression.getValue(context, A.class);
        System.out.println(a.getName());

        Expression expression1 = parser.parseExpression("#v");
        System.out.println(expression1.getValue(context));
    }

}
