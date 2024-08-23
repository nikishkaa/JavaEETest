package org.example.javaeedemo;

import java.io.*;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "AgeServlet", value = "/age")
public class AgeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("html/age_form.html");
        rd.forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        int userAge = Integer.parseInt(request.getParameter("age"));

// Dont working
        if (userAge >= 18) {
            response.sendRedirect("https://learn.javascript.ru/");
        }
    }

}
