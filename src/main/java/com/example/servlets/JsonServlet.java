package com.example.servlets;

import com.example.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.utils.ServerUtils.createUser;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserModel user = createUser(1, "Dima", 29);
        String value = new ObjectMapper().writeValueAsString(user);
        resp.getWriter().write(value);
    }
}
