package com.example.utils;

import com.example.dao.UserDao;
import com.example.models.UserModel;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerUtils {

    public static boolean stringIsNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isInteger(String str) {
        return stringIsNotEmpty(str) && str.matches("\\d+");
    }

    public static boolean isUserDataValid(String name, String age){
        return stringIsNotEmpty(name) && isInteger(age);
    }

    public static <T> T getDaoByKey(ServletContext servletContext, String key, Class<T> clazz) {
        Object dao = servletContext.getAttribute(key);
        if(!(dao.getClass()).isAssignableFrom(clazz)){
            throw new RuntimeException("Something went wrong with the repository.");
        }
        return (T) dao;
    }

}
