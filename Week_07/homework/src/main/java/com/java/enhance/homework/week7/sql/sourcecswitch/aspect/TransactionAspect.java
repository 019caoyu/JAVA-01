package com.java.enhance.homework.week7.sql.sourcecswitch.aspect;

import com.java.enhance.homework.week7.sql.sourcecswitch.config.DBSourceSwitchHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
public class TransactionAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Around("@annotation(com.java.enhance.homework.week7.sql.sourcecswitch.annotation.ReadTrans)")
    public Object doWithReadTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Connection readConnection = (Connection) applicationContext.getBean("slaveConnection");
        DBSourceSwitchHandler.switchDataSourceConnection(readConnection);
        try {
            return joinPoint.proceed();
        }finally {
            try {
                readConnection.close();
                DBSourceSwitchHandler.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Around("@annotation(com.java.enhance.homework.week7.sql.sourcecswitch.annotation.WriteTrans)")
    public Object doWithWriteTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Connection writeConnection = (Connection) applicationContext.getBean("masterConnection");
        DBSourceSwitchHandler.switchDataSourceConnection(writeConnection);
        try {
            writeConnection.setAutoCommit(false);
            Object result =  joinPoint.proceed();
            writeConnection.commit();
            return result;
        }catch (Throwable throwable) {
            writeConnection.rollback();
            throw  throwable;
        }finally {
            try {
                writeConnection.close();
                DBSourceSwitchHandler.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
