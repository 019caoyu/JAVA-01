package com.java.enhance.homework.week7.sql.sourcecswitch.service;

import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;
import com.java.enhance.homework.week7.sql.sourcecswitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }
}
