package org.example.javaeedemo;

import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "Servlet", value = "/show-cars")
public class CarsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("cars", Arrays.asList("BMW", "HONDA", "OPEL"));
        ServletUtils.forwardJsp("cars-table", request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
