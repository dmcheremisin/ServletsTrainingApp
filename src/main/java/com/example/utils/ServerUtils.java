package com.example.utils;

import com.example.models.UserModel;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerUtils {
    public static UserModel createUser(int id, String name, int age){
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setAge(age);
        return userModel;
    }

    public static Map<Integer, UserModel> getUsersMap(Object users) {
        if(!(users instanceof ConcurrentHashMap)){
            throw new IllegalArgumentException("Something went wrong with the repository.");
        }
        return (ConcurrentHashMap<Integer, UserModel>) users;
    }

    public static boolean stringIsNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isInteger(String str) {
        return stringIsNotEmpty(str) && str.matches("\\d+");
    }

    public static boolean isUserDataValid(String name, String age){
        return stringIsNotEmpty(name) && isInteger(age);
    }

    public static Map<Integer, UserModel> getUsersFromContext(ServletContext servletContext) {
        Object users = servletContext.getAttribute("users");
        return getUsersMap(users);
    }

}
