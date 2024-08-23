package org.example.javaeedemo;

import java.io.*;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

    @WebServlet(name = "RDServlet", value = "/rd-test")
public class RDServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Request Session ID " + request.getRequestedSessionId());

        String val = Optional.ofNullable(request.getParameter("param")).orElse("empty");

        if (val.equals("page1")) { // ...?param=page1
            RequestDispatcher rd = request.getRequestDispatcher("page1.html");
            rd.forward(request, response);
        }else{
            response.sendRedirect("https://learn.javascript.ru/"); // переодресация сюда если параметр не равен "page1"
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

}
