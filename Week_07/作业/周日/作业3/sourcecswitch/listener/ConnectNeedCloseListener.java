package com.java.enhance.homework.week7.sql.sourcecswitch.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectNeedCloseListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private Connection slaveConnection;

    @Autowired
    private Connection masterConnection;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            masterConnection.close();
            slaveConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
