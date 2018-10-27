package com.example.dao;

import com.example.constants.ROLE;
import com.example.models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection;
    private final String SELECT_QUERY = "SELECT * FROM USERSCREDS";
    private final String INSERT_QUERY = "INSERT INTO USERSCREDS (NAME, AGE, LOGIN, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?);";
    private final String UPDATE_QUERY = "UPDATE USERSCREDS SET NAME = ? , AGE = ? , LOGIN = ? , PASSWORD = ? , ROLE = ? WHERE ID = ?;";
    private final String DELETE_QUERY = "DELETE FROM USERSCREDS WHERE ID = ?";

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    private Statement getStatement(){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        try(Statement stmt = connection.createStatement();) {
            ResultSet rs = stmt.executeQuery(SELECT_QUERY);
            while (rs.next()) {
                users.add(parseUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private UserModel parseUser(ResultSet rs) throws SQLException {
        UserModel userModel = new UserModel();
        userModel.setId(rs.getInt("id"));
        userModel.setName(rs.getString("name"));
        userModel.setAge(rs.getInt("age"));
        userModel.setLogin(rs.getString("login"));
        userModel.setPassword(rs.getString("password"));
        userModel.setRole(ROLE.getRoleByKey(rs.getString("role")));
        return userModel;
    }

    public boolean isUserExists(String login, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY + " WHERE LOGIN = ? AND PASSWORD = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserModel getUserByCredentials(String login, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY + " WHERE LOGIN = ? AND PASSWORD = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return parseUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel getUserById(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY + " WHERE ID = ? ");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return parseUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(String name, int age, String login, String password, String role) {
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setLogin(login);
        userModel.setPassword(password);
        userModel.setRole(ROLE.getRoleByKey(role));
        addUserModel(userModel);
    }

    public void addUserModel(UserModel user) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);){
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole().getRoleString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserModel(UserModel user) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);){
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole().getRoleString());
            stmt.setInt(6, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UserModel userModel) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);){
            stmt.setInt(1, userModel.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
