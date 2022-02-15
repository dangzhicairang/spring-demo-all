package com.example.springdemoall.boot.lambdasafe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.util.LambdaSafe;

public class TestMain {

    @Test
    public void test() {
        HelloService instance = new HelloService();
        Customizer<HelloService> customizer = HelloService::callback;

        // 必须指定正确的泛型与 instance 匹配
        // Customizer<LambdaSafe> customizer = t -> {};

        LambdaSafe.callback(
                Customizer.class
                , customizer
                , instance
                , null
        )
                .invoke(customizer1 -> customizer1.customize(instance));
    }
}
