package com.test.springboot.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
@RequestMapping("/kaptcha/")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;
    
    @RequestMapping("createKaptcha")
    @ResponseBody
    public void createKaptcha(){
        try {
            BufferedImage image = captchaProducer.createImage("1234");
//            FileOutputStream fos = new FileOutputStream(new File("D:\\picture\\"+System.currentTimeMillis()+".jpg"));
            ImageIO.write(image, "jpg", new File("D:\\picture\\"+System.currentTimeMillis()+".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
