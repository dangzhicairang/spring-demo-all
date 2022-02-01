package com.example.springdemoall.cache;

import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;

public class BeanFactoryCacheOperationSourceAdvisorDemo {

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
        BeanFactoryCacheOperationSourceAdvisor advisor
                = new BeanFactoryCacheOperationSourceAdvisor();
        advisor.setCacheOperationSource(new AnnotationCacheOperationSource());

        // ClassFilter#matches 是委托 AnnotationCacheOperationSource 实现的
        System.out.println(advisor.getPointcut()
                .getClassFilter()
                .matches(CacheTarget.class));

        // MethodMatcher#matches 是委托 AnnotationCacheOperationSource 实现的
        System.out.println(advisor.getPointcut()
                .getMethodMatcher()
                .matches(
                        CacheTarget.class.getMethod("a", String.class)
                        , CacheTarget.class
                ));
    }
}
