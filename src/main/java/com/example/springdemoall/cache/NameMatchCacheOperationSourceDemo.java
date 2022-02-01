package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.cache.interceptor.CacheableOperation;
import org.springframework.cache.interceptor.NameMatchCacheOperationSource;

import java.util.ArrayList;
import java.util.Collection;

public class NameMatchCacheOperationSourceDemo {

    public static class TargetClass {
        public void test() {}
        public void test1() {}
    }

    @Test
    public void test() throws NoSuchMethodException {
        NameMatchCacheOperationSource cacheOperationSource
                = new NameMatchCacheOperationSource();
        // 指定方法名的正则及对应的 CacheOperation 集合
        cacheOperationSource.addCacheMethod(
                "test*"
                , new ArrayList<CacheOperation>() {{
                    add(new CacheableOperation(new CacheableOperation.Builder()));
                }}
        );

        // 获取指定方法对应的 CacheOperation 集合
        Collection<CacheOperation> test = cacheOperationSource.getCacheOperations(
                TargetClass.class.getMethod("test")
                , null
        );
        Collection<CacheOperation> test1 = cacheOperationSource.getCacheOperations(
                TargetClass.class.getMethod("test1")
                , null
        );

        test.forEach(System.out::println);
        test1.forEach(System.out::println);
    }
}
