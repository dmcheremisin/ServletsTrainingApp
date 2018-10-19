package com.example.listeners;

import com.example.models.UserModel;
import com.example.utils.ServerUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StubDataInitializer implements ServletContextListener {
    private static Map<Integer, UserModel> users = new ConcurrentHashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        UserModel dima = ServerUtils.createUser(0, "Dima", 29);
        UserModel anya = ServerUtils.createUser(1, "Anya", 30);
        users.put(dima.getId(), dima);
        users.put(anya.getId(), anya);
        servletContext.setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        users = null;
    }
}
