package com.example.springdemoall.beanfactory.event.eventlistener;

import com.example.springdemoall.beanfactory.event.ChangeEvent;
import com.example.springdemoall.beanfactory.event.CustomEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommonComponent {

    /**
     * 基于 SpEL 的条件表达式指定
     * 这里是要事件的属性 name == default 才监听
     * 单个事件的监听可以指定事件类型的参数，这里是监听 CustomEvent
     * 方法返回值是事件类型时，会在处理完当前事件后再发布对应的事件，
     *      比如这里处理完 CustomEvent 会发布一个 ChangeEvent
     */
    @EventListener(condition = "#event.name == 'default'")
    public ChangeEvent listenCustomEvent(CustomEvent event) {
        System.out.println("listen a custom event");
        return new ChangeEvent(event.getSource());
    }

    // 监听 ChangeEvent
    @EventListener
    public void listenChangeEvent(ChangeEvent event) {
        System.out.println("listen a change event");
    }

    // 可以指定监听多个事件，但不能有参数
    @EventListener(classes = { CustomEvent.class, ChangeEvent.class })
    public void listenBothEvent() {
        System.out.println("listen any event");
    }
}
