package com.example.springdemoall.cache.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    // 注册 ConcurrentMapCacheManager
    @Bean
    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager
                = new ConcurrentMapCacheManager();
        return cacheManager;
    }

    // 自定义配置 KeyGenerator
    @Bean
    @Override
    public KeyGenerator keyGenerator() {

        return new PrefixSimpleKeyGenerator("configurer");
    }

    // 注册一个 KeyGenerator 的 bean，用来在注解上指定
    @Bean
    public KeyGenerator customKeyGenerator() {

        return new PrefixSimpleKeyGenerator("custom");
    }

    // 稍微拓展一下 SimpleKeyGenerator
    public static class PrefixSimpleKeyGenerator extends SimpleKeyGenerator {

        private String prefix;

        public PrefixSimpleKeyGenerator(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Object generate(Object target, Method method, Object... params) {
            return prefix + super.generate(target, method, params);
        }
    }
}
