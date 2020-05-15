package com.test.springboot.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ajax/")
public class AjaxController {

    @RequestMapping("get")
    @ResponseBody
    public String get(HttpServletRequest request){
        // ajax 请求："XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println(name + " --> " + request.getHeader(name));
        }
        return "get";
    }
    
}
