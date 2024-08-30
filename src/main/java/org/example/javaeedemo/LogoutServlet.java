package org.example.javaeedemo;

import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.invalidate();
            System.out.println("Session with ID: " + session.getId() + " has been invalidated");

            ServletUtils.forwardJsp("blog",request,response);
        }else {
            ServletUtils.forwardJsp("login",request,response);
            return;
        }


    }

}
