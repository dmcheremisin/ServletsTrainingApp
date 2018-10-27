package com.example.listeners;

import com.example.connection.DbConnection;
import com.example.constants.ROLE;
import com.example.dao.UserDao;
import com.example.models.UserModel;
import com.example.utils.ServerUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StubDataInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserDao userDao;
        try {
            Connection connection = DbConnection.getConnection();
            createStubData(connection);
            userDao = new UserDao(connection);
        } catch (Exception e) {
            throw new RuntimeException("Can't establish a connection and add test data");
        }
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute("userDao");
    }

    private void createStubData(Connection connection) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
