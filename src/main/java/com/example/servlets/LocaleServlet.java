package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    private static final String RU_CODE = "ru";
    private static final String EN_CODE = "en";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String lang = request.getParameter("lang");

        if (session != null && lang != null) {
            if (RU_CODE.equals(lang)) {
                session.setAttribute("language", RU_CODE);
            } else {
                //English by default
                session.setAttribute("language", EN_CODE);
            }
        }
        //return back to the page from where request came
        response.sendRedirect(request.getHeader("Referer"));
    }
}
