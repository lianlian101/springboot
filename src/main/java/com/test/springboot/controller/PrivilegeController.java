package com.test.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/privilege/")
public class PrivilegeController {

    @RequestMapping("getPrivilege")
    @ResponseBody
    public List<String> getPrivilege(){
        List<String> list = new ArrayList<String>();
        list.add("select");
        list.add("add");
        list.add("update");
        list.add("del");
        return list;
    }
    
}
