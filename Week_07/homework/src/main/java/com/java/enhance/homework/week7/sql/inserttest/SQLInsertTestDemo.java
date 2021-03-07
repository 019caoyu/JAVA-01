package com.java.enhance.homework.week7.sql.inserttest;

import java.sql.*;
import java.util.Properties;

public class SQLInsertTestDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = getConnection();
        System.out.println("开始插入数据");
        Long startTime = System.currentTimeMillis();
        batchInsertDataM3(connection);
        Long endTime = System.currentTimeMillis();
        connection.close();
        System.out.println("插入完毕,用时：" + (endTime - startTime));

    }

    /**
     * 用时：121345 Millis seconds
     * @param connection
     * @throws SQLException
     */
    private static void batchInsertDataM1( Connection connection ) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement=connection.prepareStatement("insert into insert_test(id) values(?)");


        for (int i = 0; i <1000000 ; i++) {
            statement.setInt(1,i);
            //将要执行的SQL语句先添加进去，不执行
            statement.addBatch();
        }
        //100W条SQL语句已经添加完成，执行这100W条命令并提交
        statement.executeBatch();
        connection.commit();
    }

    /**
     * 用时：101565 Millis seconds
     * @param connection
     * @throws SQLException
     */
    private static void batchInsertDataM2( Connection connection ) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement=connection.prepareStatement("insert into insert_test(id) values(?)");


        for (int i = 0; i <1000000 ; i++) {
            statement.setInt(1,i);
            //将要执行的SQL语句先添加进去，不执行
            statement.addBatch();
            if (i%500==0){
                // 500条SQL语句已经添加完成，执行这500条命令并提交
                statement.executeBatch();
                connection.commit();
            }
        }
        statement.executeBatch();
        connection.commit();
    }

    /**
     * 用时：9462 Millis seconds
     * @param connection
     * @throws SQLException
     */
    private static void batchInsertDataM3( Connection connection ) throws SQLException {
        connection.setAutoCommit(false);
        //PreparedStatement statement=connection.prepareStatement("insert into insert_test(id) values(?)");
        Statement statement = connection.createStatement();
        StringBuilder batchInsertSql = new StringBuilder("insert into insert_test(id) values ");
        for (int i = 1; i <=1000000 ; i++) {
            batchInsertSql.append("(").append(i).append(")").append(",");
            if (i%10000==0) {
                batchInsertSql.deleteCharAt(batchInsertSql.lastIndexOf(","));
                statement.addBatch(batchInsertSql.toString());
                batchInsertSql = new StringBuilder("insert into insert_test(id) values ");
            }
        }
        statement.executeBatch();

        connection.commit();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Driver driver = DriverManager.getDriver("jdbc:mysql://localhost:3306/db2?characterEncoding=utf8");
        Properties connectProperties = new Properties();
        connectProperties.setProperty("user","root");
        connectProperties.setProperty("password","root");
        return driver.connect("jdbc:mysql://localhost:3306/db2?characterEncoding=utf8", connectProperties);
    }
}
