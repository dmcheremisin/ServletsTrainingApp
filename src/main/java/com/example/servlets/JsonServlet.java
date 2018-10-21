package com.example.servlets;

import com.example.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.example.utils.ServerUtils.getUsersFromContext;
import static com.example.utils.ServerUtils.isUserDataValid;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        Map<Integer, UserModel> usersFromContext = getUsersFromContext(getServletContext());
        Collection<UserModel> users = usersFromContext.values();
        String value = new ObjectMapper().writeValueAsString(users);

        resp.getWriter().write(value);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, UserModel> usersFromContext = getUsersFromContext(getServletContext());
        int size = usersFromContext.size();

        String userString = req.getParameter("user");
        UserModel userModel = new ObjectMapper().readValue(userString, UserModel.class);
        if(isUserDataValid(userModel.getName(), userModel.getAge() + "")){
            usersFromContext.put(size, userModel);
        } else {
            throw new IllegalArgumentException("There is no such user in the system.");
        }
    }
}
