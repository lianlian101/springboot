package com.test.springboot.demo.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Test;

public class Captcha {

    @Test
    public void demo_1() throws FileNotFoundException, IOException{
        BufferedImage image = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        Graphics gp = image.getGraphics();
        gp.drawString("Hello World", 0, 40);
        gp.dispose();
        ImageIO.write(image, "JPG", new FileOutputStream(new File("D://captcha.jpg")));
    }
    
    @Test
    public void demo_2() throws FileNotFoundException, IOException{
        
        String str = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM"; // 验证码
        int width = 120; // 验证码图片的宽度
        int height = 60; // 验证码图片的高度
        int lines = 10; // 干扰线个数
        int count = 4; // 验证码个数
        
        Random random = new Random();
        
        // 获取一个图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        // 设置背景颜色
        g.setColor(Color.WHITE);
        // 画背景颜色
        g.fillRect(0, 0, width, height);
        
        // 设置字体
        g.setFont(new Font("微软雅黑", Font.BOLD, 26));
        for(int i = 0; i < count; i++){
            // 设置颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            // 往图片里写字
            g.drawString(str.charAt(random.nextInt(36))+"", 15 + width/5*i, height/6*3+8);
        }
        
        // 干扰线
        for(int i = 0; i < lines; i++){
            // 设置颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            // 绘制干扰线
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
        
        // 释放资源
        g.dispose();
        
        // 图片写入磁盘
        ImageIO.write(image, "JPG", new FileOutputStream(new File("D://captcha.jpg")));
        
    }

}
