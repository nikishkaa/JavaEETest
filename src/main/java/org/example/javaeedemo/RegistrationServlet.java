package org.example.javaeedemo;

import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.EncryptDecryptUtils;
import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
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

            userDAO.createUser(user);
            ServletUtils.forwardJsp("blog", request, response);
            return;
        } else {
            response.getWriter().println("Password missing");
            return;
        }


    }
}
