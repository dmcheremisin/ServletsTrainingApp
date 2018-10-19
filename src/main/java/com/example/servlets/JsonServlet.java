package com.example.servlets;

import com.example.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.example.utils.ServerUtils.createUser;
import static com.example.utils.ServerUtils.getUsersFromContext;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        Map<Integer, UserModel> usersFromContext = getUsersFromContext(getServletContext());
        Collection<UserModel> users = usersFromContext.values();
        String value = new ObjectMapper().writeValueAsString(users);

        resp.getWriter().write(value);
    }
}
