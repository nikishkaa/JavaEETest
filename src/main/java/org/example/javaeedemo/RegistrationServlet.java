package org.example.javaeedemo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.EncryptDecryptUtils;
import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "RegistrationServlet", value = "/reg")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("login page request " + new Date());

        ServletUtils.forwardJsp("register_form", request, response);
//TODO доделать вход сразу при регистации
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("psw");
        String repeatedPassword = request.getParameter("psw2");

        if (password.equals(repeatedPassword)) {
            User user = new User();
            user.setEmail(email);
            user.setName(name);

            String encryptedPassword = EncryptDecryptUtils.encrypt(password);
            user.setPassword(encryptedPassword);

            UserDAOImpl userDAO = new UserDAOImpl();
            try {
                boolean isCreated = userDAO.createUser(user);
                if (isCreated) {
                    ServletUtils.forwardJsp("blog", request, response);
                } else {
                    request.setAttribute("msg", "Error User Registration");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg", "Internal Error User Registration");
                String message = ExceptionUtils.getMessage(e.getCause());
                String stackTrace = ExceptionUtils.getStackTrace(e);
                request.setAttribute("cause", StringUtils.isEmpty(message) ? ExceptionUtils.getMessage(e) : message); // извлекает сообщение
                request.setAttribute("stack-trace", stackTrace); // извлекает сообщение

                ServletUtils.forwardJsp("register_form", request, response);
                return;
            }
            return;
        } else {
            response.getWriter().println("Password missing");
            return;
        }


    }
}
