package com.java.enhance.homework.week7.sql.sourcecswitch;

import com.java.enhance.homework.week7.sql.sourcecswitch.config.DBSourceConfigration;
import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;
import com.java.enhance.homework.week7.sql.sourcecswitch.service.DefaultUserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SourceSwitchDemo {


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBSourceConfigration.class);

        //Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        DefaultUserServiceImpl defaultUserService =  context.getBean(DefaultUserServiceImpl.class);
        User user = new User();
        user.setUserName("test2");
        user.setUserAge(10);
        defaultUserService.addUser(user);
        Thread.sleep(1000);
        System.out.println(defaultUserService.getUserByName("test"));

        context.close();


    }
}
