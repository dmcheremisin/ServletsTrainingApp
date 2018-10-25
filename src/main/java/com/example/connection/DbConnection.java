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

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE USERSCREDS\n" +
                    "(\n" +
                    "  id int AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                    "  name VARCHAR(100),\n" +
                    "  age int NOT NULL,\n" +
                    "  login varchar(100),\n" +
                    "  password VARCHAR(100),\n" +
                    "  role VARCHAR(20)\n" +
                    ");");
            statement.execute("INSERT INTO USERSCREDS (NAME, AGE, LOGIN, PASSWORD, ROLE) VALUES ('Dima', 29, 'admin', '123', 'admin');");
            statement.execute("INSERT INTO USERSCREDS (NAME, AGE, LOGIN, PASSWORD, ROLE) VALUES ('Anya', 29, 'anya', '123', 'member');");
            ResultSet rs = statement.executeQuery("SELECT * FROM USERSCREDS;");
            while (rs.next()) {
                System.out.println("id = " + rs.getString(1) + "; name = " + rs.getString(2) );
            }
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
