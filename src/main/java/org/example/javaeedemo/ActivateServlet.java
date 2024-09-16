package org.example.javaeedemo;

import org.example.javaeedemo.dao.UserDAOImpl;
import org.example.javaeedemo.dao.UsersDao;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.EncryptDecryptUtils;
import org.example.javaeedemo.utils.ServletUtils;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "ActivateServlet", value = "/activate")
public class ActivateServlet extends HttpServlet {

    private UsersDao userDAO = new UsersDao();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = request.getParameter("token");
        if (token != null) {
            // escape chars
            // '+' ->' '
            token = token.replaceAll(" ", "+");

            String email = EncryptDecryptUtils.decrypt(token);
            User user = userDAO.findByEmail(email);
            if (user != null) {
                // User found & should be activated
                boolean isActivated = userDAO.activate(email);
                if (isActivated) {
                    request.setAttribute("msg", "WELCOME. YOUR ARE ACTIVATED");
                    ServletUtils.forwardJsp("blog", request, response);
                    return;
                }
            }
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
