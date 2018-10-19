package com.example.servlets;

import com.example.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.utils.ServerUtils.createUser;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        UserModel dima = createUser(1, "Dima", 29);
        UserModel anya = createUser(2, "Anya", 29);
        List<UserModel> users = Arrays.asList(dima, anya);
        String value = new ObjectMapper().writeValueAsString(users);

        resp.getWriter().write(value);
    }
}
