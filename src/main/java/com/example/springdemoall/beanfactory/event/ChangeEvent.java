package com.example.springdemoall.beanfactory.event;

import org.springframework.context.ApplicationEvent;

public class ChangeEvent extends ApplicationEvent {

    public ChangeEvent(Object source) {
        super(source);
    }
}
