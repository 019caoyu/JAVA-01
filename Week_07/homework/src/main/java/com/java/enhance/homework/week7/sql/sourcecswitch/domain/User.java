package com.java.enhance.homework.week7.sql.sourcecswitch.domain;

public class User {

    private int id;

    private String userName;

    private int userAge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", userAge=").append(userAge);
        sb.append('}');
        return sb.toString();
    }
}
