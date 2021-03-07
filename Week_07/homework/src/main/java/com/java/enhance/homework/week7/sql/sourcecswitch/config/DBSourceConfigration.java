package com.java.enhance.homework.week7.sql.sourcecswitch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan("com.java.enhance.homework.week7.sql.sourcecswitch")
public class DBSourceConfigration {

    @Bean
    public Connection masterConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Driver driver = DriverManager.getDriver("jdbc:mysql://127.0.0.1:3306/db2?characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        Properties connectProperties = new Properties();
        connectProperties.setProperty("user","root");
        connectProperties.setProperty("password","root");
        Connection writeC= driver.connect("jdbc:mysql://127.0.0.1:3306/db2?characterEncoding=utf8&serverTimezone=Asia/Shanghai", connectProperties);
        return writeC;
    };


   /* @Bean
    public Connection slaveConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Driver driver = DriverManager.getDriver("jdbc:mysql://127.0.0.1:3308/db2?characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        Properties connectProperties = new Properties();
        connectProperties.setProperty("user","root");
        connectProperties.setProperty("password","root");
        Connection readC =  driver.connect("jdbc:mysql://127.0.0.1:3308/db2?characterEncoding=utf8&serverTimezone=Asia/Shanghai", connectProperties);
        return readC;
    };*/




}


