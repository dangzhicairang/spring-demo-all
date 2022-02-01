package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class ConcurrentMapCacheManagerDemo {

    @Test
    public void test() {
        ConcurrentMapCacheManager cacheManager
                = new ConcurrentMapCacheManager("test1", "test2");
        cacheManager.getCache("test1")
                .get("1", () -> "a");

        // 指定了 cacheNames，无法创建缺省缓存，NPE
        cacheManager.getCache("test3")
                .get("1", () -> "b");

    }
}
