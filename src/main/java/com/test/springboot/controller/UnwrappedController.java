package com.test.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springboot.entity.pojo.Name;
import com.test.springboot.entity.pojo.Parent;

@Controller
@RequestMapping("/unwrapped/")
public class UnwrappedController {
    
    @RequestMapping("get")
    @ResponseBody
    public Parent get(){
        Name name = new Name();
        name.setFirstName("张");
        name.setLastName("三");
        Parent parent = new Parent();
        parent.setAge(11);
        parent.setName(name);
        return parent;
    }
    
    @RequestMapping("post")
    @ResponseBody
    public String post(@RequestBody Parent parent){
        System.out.println(parent);
        return "post";
    }

}
