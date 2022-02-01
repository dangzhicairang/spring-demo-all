package com.example.springdemoall.configurer;

public interface SampleComponentConfigurer {

    default String getName() {

        return "default";
    }

    default Integer getAge() {

        return 18;
    }

}
