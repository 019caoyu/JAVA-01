package com.java.enhance.homework.week7.sql.sourcecswitch.service;

import com.java.enhance.homework.week7.sql.sourcecswitch.annotation.ReadTrans;
import com.java.enhance.homework.week7.sql.sourcecswitch.annotation.WriteTrans;
import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;
import com.java.enhance.homework.week7.sql.sourcecswitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserServiceImpl /*implements UserService*/{
    // if implements UserService spring use java dynamic proxy to implement AOP

    @Autowired
    private UserRepository userRepository;


    @WriteTrans
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }


    @ReadTrans
    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }
}
