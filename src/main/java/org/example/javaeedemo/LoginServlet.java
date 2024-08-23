package org.example.javaeedemo;

import java.io.*;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("login page request " + new Date());
        request.getRequestDispatcher("/html/login.html").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("first");
        String userPassword = request.getParameter("password");

        // Check in DB

        if (userName.equalsIgnoreCase("John")) {
            if (userPassword.equals("1234")) {
                response.getWriter().println("Welcome back John");
                return;
            } else {
                // include
                response.setContentType("text/html");
                response.getWriter().println("<h2>Incorrect userName or password</h2>");

                RequestDispatcher rd = request.getRequestDispatcher("/html/login.html");
                rd.include(request, response);
                return;
            }
        }


    }

}
