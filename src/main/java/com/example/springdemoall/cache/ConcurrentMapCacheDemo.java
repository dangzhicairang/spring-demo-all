package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.concurrent.ConcurrentMapCache;

public class ConcurrentMapCacheDemo {

    @Test
    public void test() {
        ConcurrentMapCache cache = new ConcurrentMapCache("test");
        cache.put("1", "a");
        cache.get("2", () -> null);
        System.out.println(cache.get("1").get());
        System.out.println(cache.get("2").get());

        cache.clear();
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
    }
}
