package com.example.servlets;

import com.example.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.example.utils.ServerUtils.*;

public class UsersPageServlet extends HttpServlet {

    private Map<Integer, UserModel> getUsersFromContext() {
        Object users = getServletContext().getAttribute("users");
        return getUsersMap(users);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, UserModel> usersModels = getUsersFromContext();
        req.setAttribute("users", usersModels.values());
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, UserModel> users = getUsersFromContext();
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if(isUserDataValid(name, age)){
            int size = users.size();
            int intAge = Integer.parseInt(age);
            UserModel user = createUser(size, name, intAge);
            users.put(size, user);
        } else {
            throw new IllegalArgumentException("Please, provide valid user data.");
        }
        doGet(req, resp);
    }


}
