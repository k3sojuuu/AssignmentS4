package com.example.demo.database;

import java.sql.*;

public class Database {
    private final String connectionUrl = "jdbc:mysql://localhost:3306/T2302";
    private final String username = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    private static Database instance;
    private Database() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Connection getConnection() {
        return connection;
    }
}
