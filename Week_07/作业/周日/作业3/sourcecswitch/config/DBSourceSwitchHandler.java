package com.java.enhance.homework.week7.sql.sourcecswitch.config;

import java.sql.Connection;

public class DBSourceSwitchHandler {

    private static  ThreadLocal<Connection> sourceTypeConnection = new ThreadLocal<>();

    public static void switchDataSourceConnection(Connection typeConnection){
        sourceTypeConnection.set(typeConnection);
    }

    public static Connection getDataSourceConnection(){
        return sourceTypeConnection.get();
    }

    public static void clear(){
        sourceTypeConnection.remove();
    }

}
