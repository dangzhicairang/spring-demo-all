package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ExtendSimpleCacheManagerDemo {

    // 拓展 SimpleCacheManager 提供了缺省缓存实现：new ConcurrentMapCache(name)
    public static class ExtendSimpleCacheManager extends SimpleCacheManager {

        @Override
        protected Cache getMissingCache(String name) {

            return new ConcurrentMapCache(name);
        }
    }

    @Configuration
    public static class Config {

        @Bean
        public CacheManager cacheManager() {
            ExtendSimpleCacheManager cacheManager
                    = new ExtendSimpleCacheManager();

            // 创建一个默认缓存
            ConcurrentMapCache cache = new ConcurrentMapCache("test");
            cache.put("1", "a");
            List<Cache> caches = new ArrayList<Cache>() {{
                add(cache);
                // ...
            }};
            cacheManager.setCaches(caches);

            return cacheManager;
        }
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Config.class);
        CacheManager bean = applicationContext.getBean(CacheManager.class);

        // 第一个缓存是被 loadCaches 的
        System.out.println(bean.getCache("test")
                .get("1").get());

        // 创建了缺省缓存
        bean.getCache("test2")
                .put("1", "b");

        bean.getCacheNames()
                .forEach(System.out::println);
    }
}
