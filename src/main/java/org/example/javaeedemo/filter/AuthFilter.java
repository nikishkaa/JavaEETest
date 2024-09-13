//package org.example.javaeedemo.filter;
//
//import org.example.javaeedemo.model.Role;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//@WebFilter(filterName = "AuthFilter")
//public class AuthFilter implements Filter {
//    private Map<Role, List<String>> authMap;
//    private List<String> whiteList;
//
//    public void init(FilterConfig config) throws ServletException {
//
//        Role admin = new Role(1, "ADMIN", null);
//        Role manager = new Role(2, "MANAGER", null);
//        Role gu = new Role(3, "GENERAL_USER", null);
//
//        authMap.put(admin, Arrays.asList("/show-cars", "/blog"));
//        authMap.put(manager, Arrays.asList("/show-cars"));
//        authMap.put(gu, Arrays.asList("/blog"));
//
//        whiteList = Arrays.asList("/login", "/reg", "/basic-msg");
//        //...
//
//
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String path = httpServletRequest.getServletPath();
//        if (whiteList.contains(path)) {
//            System.out.println(path + " in white list");
//            chain.doFilter(request, response);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//}