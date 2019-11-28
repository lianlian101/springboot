package com.test.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/jsonp")
public class JsonpController {

    /**
     * 日期：2019年10月25日
     * 作者：zhb
     * 说明：jsonp测试
     * 
     * @param callback
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    public String index(String callback){
        String message = "{\"name\":\"张三\",\"age\":\"30\"}";
        String json = JSON.toJSONString(message);
        String result = callback + "(" + json + ")";
        return result;
    }
    
}
