package com.test.springboot.demo.test;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMailTest {
    
    @Value("${mail.from}")
    private String from;
    @Value("${mail.to}")
    private String to;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    /**
     * 简单文件发送
     */
    @Test
    public void simpleMailMessage(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject("测试");
        mailMessage.setText("这是一封测试邮件");
        javaMailSender.send(mailMessage);
    }
    
    /**
     * 添加附件发送
     */
    @Test
    public void messageHelper(){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //是否发送的邮件是富文本（附件，图片，html等）
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            //第二个参数是邮件别名
            messageHelper.setFrom(from, "测试");
            messageHelper.setTo(to);
            messageHelper.setSubject("html测试");
            //默认为false，显示原始html代码，无效果
            messageHelper.setText("<a href=\"https://www.gatebim.com\">网址</a>", true);
            //添加附件
            File file = new File("D://picture/1.jpg");
            if(file.exists()){
                messageHelper.addAttachment("附件", new FileSystemResource(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

}
