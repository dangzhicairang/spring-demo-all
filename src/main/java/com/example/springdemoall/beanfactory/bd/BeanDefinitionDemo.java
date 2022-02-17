package com.example.springdemoall.beanfactory.bd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

public class BeanDefinitionDemo {

    @Component("test")
    public static class A {

        public String name = "default";

        public void a() {
            System.out.println("init ...");
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
        GenericBeanDefinition beanDefinition
                = new GenericBeanDefinition();
        beanDefinition.setBeanClass(A.class);
        // init-method 指定
        beanDefinition.setInitMethodName("a");
        // 属性注入依赖模式
        beanDefinition.setPropertyValues(
                new MutablePropertyValues()
                        .add("name", "dd")
        );

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();
        // 注册 bd
        context.registerBeanDefinition("test", beanDefinition);
        // 容器启动时会基于 bd 创建对应的 bean
        context.refresh();
        A bean = context.getBean(A.class);
        System.out.println(bean.name);
    }

    @Test
    public void test2() {

        // 元数据解析
        AnnotationMetadata introspect = AnnotationMetadata.introspect(A.class);
        // AnnotatedBeanDefinition 会根据传入的元数据解析 beanClass
        AnnotatedBeanDefinition beanDefinition
                = new AnnotatedGenericBeanDefinition(introspect);
        // 注册 bd，beanName 从注解元数据上解析
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        Map<String, Object> attributes
                = metadata.getAnnotationAttributes("org.springframework.stereotype.Component");
        String beanName = attributes.get("value").toString();

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();
        context.registerBeanDefinition(beanName, beanDefinition);
        // 容器启动时会基于 bd 创建对应的 bean
        context.refresh();
        A bean = context.getBean("test", A.class);
        System.out.println(bean.name);
    }
}
