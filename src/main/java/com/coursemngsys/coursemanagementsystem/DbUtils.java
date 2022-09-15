package com.coursemngsys.coursemanagementsystem;


import java.sql.*;

public class DbUtils {

    public static void createDb(){
        String DB_URL = "jdbc:mysql://localhost";
        String USER = "root";
        String PASS = "1234";
        String sql = "CREATE DATABASE IF NOT EXISTS course_management_system";
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDb() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/course_management_system";
            String USER = "root";
            String PASS = "1234";
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException t) {
            t.printStackTrace();
        }
        return connection;
    }

    public static void disconnectFromDb(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
