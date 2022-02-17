package com.example.springdemoall.beanfactory.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public interface HelloService {

    @Component
    @Qualifier("1")
    class HelloServiceImpl1 implements HelloService {}

    @Component
    @Qualifier("2")
    class HelloServiceImpl2 implements HelloService {}

    @Component
    class Container1 {

        @Autowired
        @Qualifier("1")
        HelloService helloService;

        public HelloService getHelloService() {
            return helloService;
        }

        public void setHelloService(HelloService helloService) {
            this.helloService = helloService;
        }
    }
}
