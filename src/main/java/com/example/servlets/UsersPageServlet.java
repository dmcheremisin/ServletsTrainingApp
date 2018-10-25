package com.example.servlets;

import com.example.dao.UserDao;
import com.example.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.utils.ServerUtils.*;

public class UsersPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = getDaoByKey(getServletContext(), "userDao",  UserDao.class);
        List<UserModel> users = userDao.getUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = getDaoByKey(getServletContext(), "userDao",  UserDao.class);
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if(isUserDataValid(name, age)){
            int intAge = Integer.parseInt(age);
            userDao.addUser(name, intAge, null, null, null);
        } else {
            throw new IllegalArgumentException("Please, provide valid user data.");
        }
        doGet(req, resp);
    }
}
