package org.example.javaeedemo;

import org.example.javaeedemo.utils.IOUtils;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

@WebServlet(name = "Servlet", value = "/template")
public class TemplateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        File f = new File("D:\\Programming\\ee-projects\\javaee-demo\\src\\main\\webapp\\index.html");
        String indexContent = IOUtils.read(f);

        if (request.getParameter("user") != null) {
            indexContent = indexContent.replace("${action.link}", "logout"); //
            indexContent = indexContent.replace("${action.name}", "Logout"); //
        } else {
            indexContent = indexContent.replace("${action.link}", "login"); //
            indexContent = indexContent.replace("${action.name}", "Login"); //
        }

        response.getWriter().println(indexContent);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
