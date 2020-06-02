package com.test.springboot.demo.test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class ApacheEmailTest {
    
    @Test
    public void demo() throws EmailException{
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("主机地址");
        email.setAuthenticator(new DefaultAuthenticator("邮箱", "密码"));
        email.setSSLOnConnect(true);
        email.setSmtpPort(465);
        email.setFrom("邮箱", "昵称");
        email.setSubject("主题");
        email.setMsg("这是一个测试");
        email.addTo("邮箱");
        email.send();
 
        System.out.println("Success!");
    }
    
    @Test
    public void demo2() throws EmailException{
        Email email = new SimpleEmail();
        email.setHostName("主机地址");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("邮箱", "密码"));
        email.setSSLOnConnect(true);
        email.setCharset("UTF-8");
        email.setSubject("主题");
        email.setFrom("邮箱", "昵称");
        email.addTo("邮箱");
        email.setMsg("测试");
        email.send();
        System.out.println("Success!");
    }
    
    @Test
    public void demo3() throws EmailException{
        HtmlEmail email = new HtmlEmail();
        email.setHostName("主机地址");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("邮箱", "密码"));
        email.setSSLOnConnect(true);
        email.setCharset("UTF-8");
        email.setSubject("主题");
        email.setFrom("邮箱", "昵称");
        email.addTo("邮箱");
 
        email.setHtmlMsg("<p>这是一个html样式测试<b>123456</b></p>");
        
        email.send();
 
        System.out.println("Success!");
    }

}
