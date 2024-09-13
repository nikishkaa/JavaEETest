package org.example.javaeedemo.filter;

import org.example.javaeedemo.model.Role;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "LoggerFilter", urlPatterns = {"/*"})
public class LoggerFilter implements Filter {

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println(new Date() + " -> Request:  " + httpServletRequest.getServletContext() + "/ " + httpServletRequest.getServerName()
                + "/ " + httpServletRequest.getServerPort());
        System.out.println(new Date() + " -> " + httpServletRequest.getRequestedSessionId() + "/ " + httpServletRequest.getSession());

        chain.doFilter(request, response);
    }
}
