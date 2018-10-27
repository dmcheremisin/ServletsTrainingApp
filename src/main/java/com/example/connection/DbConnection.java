package com.example.connection;

import java.sql.*;
import java.util.ResourceBundle;

public class DbConnection {
    private static ResourceBundle bundle = ResourceBundle.getBundle("connection");
    private static Connection connection;

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            synchronized (DbConnection.class) {
                if (connection == null) {
                    String url = bundle.getString("url");
                    try {
                        Class.forName("org.h2.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    connection = DriverManager.getConnection(url);
                }
            }
        }
        return connection;
    }
}
