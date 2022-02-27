package com.example.springdemoall.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class Address {
        private String name;
        private Map<String, String> metaMap;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class Person {
        private String name;
        private Address address;
    }

    @Test
    public void test() {

        Person person = new Person();
        DataBinder dataBinder = new DataBinder(person);

        dataBinder.bind(
                new MutablePropertyValues()
                        .addPropertyValues(new HashMap<String, String>() {{
                            put("name", "dd");
                            put("address.name", "中国");
                            put("address.metaMap[province]", "台湾省");
                            put("address.metaMap[city]", "高雄市");
                        }})
        );

        System.out.println(person);
    }

    @Test
    public void test2() {

        Person person = new Person();
        DataBinder dataBinder = new WebDataBinder(person);

        dataBinder.bind(
                new MutablePropertyValues()
                        .addPropertyValues(new HashMap<String, String>() {{
                            put("name", "dd");
                            put("!address.name", "中国");
                            put("!address.metaMap[province]", "台湾省");
                            put("!address.metaMap[city]", "高雄市");
                            put("address.name", "CN");
                        }})
        );

        System.out.println(person);
    }
}
