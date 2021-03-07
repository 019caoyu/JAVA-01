package com.java.enhance.homework.week7.sql.sourcecswitch.repository;

import com.java.enhance.homework.week7.sql.sourcecswitch.config.DBSourceSwitchHandler;
import com.java.enhance.homework.week7.sql.sourcecswitch.domain.User;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySQLUserRepositoryImpl implements UserRepository {

    private static final String INSERT_USER_DML_SQL =
            "INSERT INTO u_user(user_name, user_age) VALUES (?, ?)";

    private static final String QUERY_USER_BY_NAME_SQL =
            "SELECT id, user_name, user_age from u_user where user_name = ? ";


    @Override
    public boolean addUser(User user) {
        try{
            PreparedStatement preparedStatement = DBSourceSwitchHandler.getDataSourceConnection().prepareStatement(INSERT_USER_DML_SQL);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setInt(2, user.getUserAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public User getUserByName(String name) {
        try{
            PreparedStatement preparedStatement = DBSourceSwitchHandler.getDataSourceConnection().prepareStatement(QUERY_USER_BY_NAME_SQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserAge(resultSet.getInt("user_age"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
