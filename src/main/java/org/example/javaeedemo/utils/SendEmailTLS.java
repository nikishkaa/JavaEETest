package org.example.javaeedemo.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS {

    public static void main(String[] args) {

        MailUtils.sendHTMLMail("gu.nikita.777@gmail.com", "HTML", "<b>TEXT</b>", null, null);

    }

}