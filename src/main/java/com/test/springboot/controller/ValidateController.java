package com.test.springboot.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validate/")
@Validated
public class ValidateController {

    @RequestMapping("va")
    @ResponseBody
    public String validate(@NotEmpty(message="参数不能为空") String name){
        return "success";
    }
    
}
