package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.CacheOperation;

import java.util.Collection;

public class AnnotationCacheOperationSourceDemo {

    public static class CacheTarget {

        @Cacheable
        public String a(String a) {

            return "a";
        }

        @CachePut
        public String b(String b) {

            return "b";
        }
    }

    @Test
    public void test() throws NoSuchMethodException {
        AnnotationCacheOperationSource cacheOperationSource
                = new AnnotationCacheOperationSource();

        boolean candidateClass =
                cacheOperationSource.isCandidateClass(CacheTarget.class);
        System.out.println(candidateClass);

        Collection<CacheOperation> a = cacheOperationSource.getCacheOperations(
                CacheTarget.class.getMethod("a", String.class)
                , null
        );
        a.forEach(System.out::println);
    }
}
