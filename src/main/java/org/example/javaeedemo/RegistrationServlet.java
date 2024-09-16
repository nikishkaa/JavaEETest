package org.example.javaeedemo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.dao.RolesDao;
import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.dao.UsersDao;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.EncryptDecryptUtils;
import org.example.javaeedemo.utils.MailUtils;
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

//            UserDAOImpl userDAO = new UserDAOImpl(); // JDBC
            UsersDao usersDAO = new UsersDao();
            RolesDao rolesDAO = new RolesDao();

            try {
//                boolean isCreated = userDAO.createUser(user); // JDBC
                // Set default role
                user.setRole(rolesDAO.findById(3));
                usersDAO.create(user);
                boolean isCreated = usersDAO.findByEmail(email) != null;

                if (isCreated) {
                    // just created - not active!
                    // send mail with instruction

                    String subject = "Welcome to Crazy Users App";
                    String token = EncryptDecryptUtils.encrypt(user.getEmail());
                    System.out.println(token);
                    String msg = String.format("<b> To confirm your account , please <a href='http://localhost:8080/javaee1/activate?token=%s'>click</a></b>", token);


                    MailUtils.sendHTMLMail(user.getEmail(), subject, msg, null, null);
                    request.setAttribute("msg", "Check Your Email to confirm");
                    ServletUtils.forwardJsp("register_form", request, response);
                    return;
//                            ServletUtils.forwardJsp("blog", request, response);
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
