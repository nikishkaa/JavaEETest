package org.example.javaeedemo;

import org.example.javaeedemo.model.User;
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

        // 1st in Session + 2nd has role admin
        User user = ServletUtils.getUserInSession(request);
        if (user == null) {
            request.setAttribute("msg", "You should login first");
            ServletUtils.forwardJsp("basic-msg", request, response);
            return;
        } else if (!user.getRole().getName().equals("ADMIN")) {
            request.setAttribute("msg", "You should have admin role");
            ServletUtils.forwardJsp("basic-msg", request, response);
            return;
        } else {
            request.setAttribute("cars", Arrays.asList("BMW", "HONDA", "OPEL"));
            ServletUtils.forwardJsp("cars-table", request, response);
            return;
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
