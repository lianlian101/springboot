package com.test.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ac/")
public class AspectController {
    
    @RequestMapping("demo")
    @ResponseBody
    public String demo(int id, String name){
        return "success";
    }
    
    
}
