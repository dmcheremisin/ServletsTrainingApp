package com.example.servlets;

import com.example.constants.ROLE;
import com.example.dao.UserDao;
import com.example.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.example.utils.ServerUtils.getDaoByKey;
import static com.example.utils.ServerUtils.isUserDataValid;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        UserDao userDao = getDaoByKey(getServletContext(), "userDao",  UserDao.class);
        Collection<UserModel> users = userDao.getUsers();
        String value = new ObjectMapper().writeValueAsString(users);

        resp.getWriter().write(value);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userString = req.getParameter("user");
        UserModel userModel = new ObjectMapper().readValue(userString, UserModel.class);
        userModel.setRole(ROLE.UNKNOWN);

        UserDao userDao = getDaoByKey(getServletContext(), "userDao",  UserDao.class);
        if(isUserDataValid(userModel.getName(), userModel.getAge() + "")){
            userDao.addUserModel(userModel);
        } else {
            throw new IllegalArgumentException("There is no such user in the system.");
        }
    }
}
