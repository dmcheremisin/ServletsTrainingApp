package com.example.servlets;

import com.example.dao.UserDao;
import com.example.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.utils.ServerUtils.getDaoByKey;
import static com.example.utils.ServerUtils.isInteger;

public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (isInteger(id)) {
            int userId = Integer.parseInt(id);
            UserDao userDao = getDaoByKey(getServletContext(), "userDao",  UserDao.class);
            UserModel userModel = userDao.getUserById(userId);
            if (userModel != null) {
                userDao.deleteUser(userModel);
                req.getRequestDispatcher("deleteUser.jsp").forward(req, resp);
                return;
            }
        }
        throw new IllegalArgumentException("There is no such user in the system.");
    }
}
