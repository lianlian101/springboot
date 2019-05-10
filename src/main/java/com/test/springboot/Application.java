package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
public class Application extends WebMvcConfigurerAdapter{
    
    /**
     * 2019年4月13日 项目启动入口
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
     * 2019年4月13日 设置默认首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
	
}
