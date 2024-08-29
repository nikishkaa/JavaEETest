package org.example.javaeedemo;

import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "BlogServlet", value = "/blog")
public class BlogServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Blog page request " + new Date());

        ServletUtils.forwardJsp("blog", request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
