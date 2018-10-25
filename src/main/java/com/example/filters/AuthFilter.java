package com.example.filters;

import com.example.constants.ROLE;
import com.example.dao.UserDao;
import com.example.models.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.constants.ROLE.ADMIN;
import static com.example.constants.ROLE.MEMBER;
import static com.example.utils.ServerUtils.*;

public class AuthFilter implements Filter {
    private final Map<ROLE, List<String>> roleUrls = new ConcurrentHashMap<>();
    private UserDao userDao;


    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        String member = servletContext.getInitParameter("memberUrls");
        String admin = servletContext.getInitParameter("adminUrls");

        List<String> memberUrls = getConfigUrls(member);

        List<String> adminUrls = getConfigUrls(admin);
        adminUrls.addAll(memberUrls);

        roleUrls.put(ADMIN, adminUrls);
        roleUrls.put(MEMBER, adminUrls);

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
            ROLE role = user.getRole();
            request.setAttribute("role", role.getRoleString());

            List<String> urls = roleUrls.get(role);
            String requestedUrl = getRequestedUrl(request.getRequestURI());
            if(urls.contains(requestedUrl)) {
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("service/restrictionError.jsp").forward(request, response);
            }
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

    @Override
    public void destroy() {

    }
}
