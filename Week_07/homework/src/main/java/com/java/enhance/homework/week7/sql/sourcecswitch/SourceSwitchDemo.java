package com.java.enhance.homework.week7.sql.sourcecswitch;

import com.java.enhance.homework.week7.sql.sourcecswitch.config.DBSourceConfigration;
import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;
import com.java.enhance.homework.week7.sql.sourcecswitch.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SourceSwitchDemo {

    @Autowired
    private DefaultUserServiceImpl defaultUserService;

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBSourceConfigration.class);

        SourceSwitchDemo demo =  context.getBean(SourceSwitchDemo.class);
        User user = new User();
        user.setUserName("test");
        user.setUserAge(10);
        demo.defaultUserService.addUser(user);
        Thread.sleep(1000);
        System.out.println(demo.defaultUserService.getUserByName("test"));

        context.close();
    }
}
