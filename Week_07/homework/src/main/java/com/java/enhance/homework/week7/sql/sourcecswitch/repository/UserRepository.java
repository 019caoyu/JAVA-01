package com.java.enhance.homework.week7.sql.sourcecswitch.repository;

import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;

public interface UserRepository {
    boolean addUser(User user);

    User getUserByName(String name);

}
