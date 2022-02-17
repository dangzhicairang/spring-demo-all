package com.example.springdemoall.beanfactory.alias;

import org.junit.jupiter.api.Test;
import org.springframework.core.SimpleAliasRegistry;

public class SimpleAliasRegistryDemo {

    @Test
    public void test() {

        SimpleAliasRegistry registry = new SimpleAliasRegistry();
        registry.registerAlias("a", "A");
        registry.registerAlias("A", "1");

        System.out.println(registry.isAlias("a"));
        System.out.println(registry.isAlias("A"));
        System.out.println(registry.isAlias("1"));

        System.out.println(registry.canonicalName("A"));
        System.out.println(registry.canonicalName("1"));
    }
}
