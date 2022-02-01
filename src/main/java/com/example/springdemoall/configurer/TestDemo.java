package com.example.springdemoall.configurer;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class TestDemo {

    @Configuration
    @Import({ SampleComponentConfiguration.class })
    public static class Config implements SampleComponentConfigurer {

        @Override
        public String getName() {
            return "dd";
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Config.class);
        SampleComponent bean = applicationContext.getBean(SampleComponent.class);
        System.out.println(bean.getName());
        System.out.println(bean.getAge());
    }
}
