package com.example.springdemoall.demo.configsort;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @Test
    public void test() {
        new AnnotationConfigApplicationContext(ComponentScanConfig.class);
    }
}
