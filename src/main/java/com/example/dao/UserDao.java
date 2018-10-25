package com.example.dao;

import com.example.constants.ROLE;
import com.example.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserDao {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock =lock.readLock();
    private Lock writeLock =lock.writeLock();

    List<UserModel> usersList = new ArrayList<>();

    public List<UserModel> getUsers(){
        readLock.lock();
        try {
            return usersList;
        } finally {
            readLock.unlock();
        }
    }

    public boolean isUserExists(String login, String password) {
        readLock.lock();
        try {
            return usersList.stream()
                    .anyMatch(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()));
        } finally {
            readLock.unlock();
        }
    }

    public UserModel getUserByCredentials(String login, String password) {
        readLock.lock();
        try {
            return usersList.stream()
                .filter(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()))
                .findFirst().orElse(null);
        } finally {
            readLock.unlock();
        }
    }

    public UserModel getUserById(int id) {
        readLock.lock();
        try {
            return usersList.stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElse(null);
        } finally {
            readLock.unlock();
        }
    }

    public void addUser(String name, int age, String login, String password, ROLE role) {
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setLogin(login);
        userModel.setPassword(password);
        userModel.setRole(role);
        addUserModel(userModel);
    }

    public void addUserModel(UserModel model) {
        writeLock.lock();
        try{
            int size = usersList.size();
            model.setId(size);
            usersList.add(model);
        } finally {
            writeLock.unlock();
        }
    }

    public void deleteUser(UserModel userModel) {
        writeLock.lock();
        try{
            usersList.remove(userModel);
        } finally {
            writeLock.unlock();
        }
    }



}
