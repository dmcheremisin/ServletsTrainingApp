package com.example.filters;

import com.example.constants.ROLE;
import com.example.dao.UserDao;
import com.example.models.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.constants.ROLE.ADMIN;
import static com.example.utils.ServerUtils.getDaoByKey;
import static com.example.utils.ServerUtils.stringIsNotEmpty;

public class AuthFilter implements Filter {
    private List<String> memberUrls;
    private List<String> adminUrls;
    private UserDao userDao;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        String member = servletContext.getInitParameter("memberUrls");
        String admin = servletContext.getInitParameter("adminUrls");
        this.memberUrls = getConfigUrls(member);

        List<String> adminUrls = getConfigUrls(admin);
        adminUrls.addAll(memberUrls);
        this.adminUrls = adminUrls;

        UserDao userDao = getDaoByKey(servletContext, "userDao",  UserDao.class);
        this.userDao = userDao;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            UserModel user = (UserModel) session.getAttribute("user");
            request.setAttribute("role", user.getRole().getRoleString());

            filterChain.doFilter(request, response);
        } else if(stringIsNotEmpty(login) && stringIsNotEmpty(password) && userDao.isUserExists(login, password)){
            UserModel user = userDao.getUserByCredentials(login, password);
            HttpSession newSession = request.getSession();
            newSession.setAttribute("user", user);
            ROLE role = user.getRole();
            request.setAttribute("role", role.getRoleString());
            if(ADMIN.equals(role)) {
                request.getRequestDispatcher("/WEB-INF/restricted/admin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/restricted/member.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private List<String> getConfigUrls(String str) {
        String[] split = str.split(",");
        List<String> urls = new ArrayList<>();
        Collections.addAll(urls, split);
        return urls;
    }

    @Override
    public void destroy() {

    }
}
