package com.example.springdemoall.boot.lambdasafe;

@FunctionalInterface
public interface Customizer<T> {

    void customize(T instance);

}
