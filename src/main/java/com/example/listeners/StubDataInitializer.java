package com.example.listeners;

import com.example.constants.ROLE;
import com.example.dao.UserDao;
import com.example.models.UserModel;
import com.example.utils.ServerUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StubDataInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        UserDao userDao = new UserDao();
        userDao.addUser("Dima", 29, "admin", "123", ROLE.ADMIN);
        userDao.addUser("Anya", 29, "anya", "123", ROLE.MEMBER);
        servletContext.setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute("userDao");
    }
}
