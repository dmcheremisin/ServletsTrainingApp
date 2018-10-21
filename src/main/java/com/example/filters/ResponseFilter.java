package com.example.filters;

import javax.servlet.*;
import java.io.IOException;

public class ResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
