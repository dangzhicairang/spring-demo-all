package com.example.springdemoall.beanfactory.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

    public String name;

    public CustomEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
