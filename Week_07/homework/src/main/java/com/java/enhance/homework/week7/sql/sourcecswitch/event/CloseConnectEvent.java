package com.java.enhance.homework.week7.sql.sourcecswitch.event;

import org.springframework.context.ApplicationEvent;

public class CloseConnectEvent extends ApplicationEvent {
    public CloseConnectEvent(Object source) {
        super(source);
    }
}
