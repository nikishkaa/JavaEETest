package org.example.javaeedemo;

import org.apache.commons.lang3.StringUtils;
import org.example.javaeedemo.utils.XMLCurrencyParser;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;

import static org.example.javaeedemo.utils.ServletUtils.*;

@WebServlet(name = "CurrenciesServlet", value = "/ex-rate")
public class CurrenciesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String path = "currencies_request";

        if (StringUtils.isEmpty(request.getParameter("amount"))) {
            // 0. show currencies request form
            forward(path, request, response);
            return;
        }

        // 1. amount & currency are presented -> client request to calculate
        // 1.1 get param values
        long amount = getLongParameter(request, "amount");
        String code = request.getParameter("code");

        // 1,2 Get currency rate
        double rate = Double.parseDouble(XMLCurrencyParser.getCurrency(code));

        // 1.3 Calculate final amount
        double result = amount * rate;

        // 1.4 Make response
        include(path, "Result Amount : " + result, request, response);
        return;

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

}
