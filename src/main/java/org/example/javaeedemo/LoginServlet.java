package org.example.javaeedemo;

import com.mysql.cj.Session;
import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("login page request " + new Date());

        ServletUtils.forwardJsp("login", request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("psw");

        User user = null;

        if ((user = userDAO.findByEmail(email)) != null) {
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(90); // Сколько времени пользователь будет в сессии

                // Store user object within HTTP Session
                session.setAttribute("user", user);

                ServletUtils.forwardJsp("blog", request, response);
                return;
            } else {
                response.getWriter().println("Bad credentials");
                return;
            }
        }
    }
}
