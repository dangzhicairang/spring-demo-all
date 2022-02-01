package com.example.springdemoall.cache.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
@CacheConfig(cacheNames = "default")
public class DemoHandler {

    // 内存管理一组 User
    static MultiValueMap<String, User> map = new LinkedMultiValueMap();

    static {
        map.add("a", new User(1, "a"));
        map.add("a", new User(2, "a"));
        map.add("b", new User(3, "b"));
    }

    @Cacheable(cacheNames = "core")
    public List<User> query(String name) {

        System.out.println("method query invoke ...");
        return map.get(name);
    }

    @CacheEvict(cacheNames = "core", allEntries = true)
    public void add(User user) {

        System.out.println("method add invoke ...");
        map.add(user.getName(), user);
    }

    @CacheEvict(allEntries = true)
    public void add2(User user) {

        System.out.println("method add2 invoke ...");
        map.add(user.getName(), user);
    }

    @CacheEvict(cacheNames = "core", keyGenerator = "customKeyGenerator")
    public void add3(User user) {

        System.out.println("method add3 invoke ...");
        map.add(user.getName(), user);
    }

    public static class User {

        private Integer id;
        private String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
