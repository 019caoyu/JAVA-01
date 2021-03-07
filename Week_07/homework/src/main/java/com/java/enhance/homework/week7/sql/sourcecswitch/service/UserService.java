package com.java.enhance.homework.week7.sql.sourcecswitch.service;

import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;

public interface UserService {

    boolean addUser(User user);

    User getUserByName(String name);
}
