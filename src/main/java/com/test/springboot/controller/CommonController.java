package com.test.springboot.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.test.springboot.entity.User;

@Controller
@RequestMapping("/common/")
public class CommonController {
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;

    @GetMapping("get")
    @ResponseBody
    public String get(@RequestParam(name = "name", required = false)String name, @RequestParam(name = "age", required = false)Integer age){
        System.out.println("name:"+name+",age:"+age);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ":" + headerValue);
        }
        Map<String,Object> map = new HashMap<String, Object>();
        if(name != null)
            map.put("name", name);
        if(age != null)
            map.put("age", age);
        String js = JSONObject.toJSONString(map);
        return js;
    }
    
    @GetMapping("getJson")
    @ResponseBody
    public User getJson(@RequestBody User user){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ":" + headerValue);
        }
        return user;
    }
    
    @PostMapping("post")
    @ResponseBody
    public String post(User user){
        System.out.println("user:"+user);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ":" + headerValue);
        }
        return JSONObject.toJSONString(user);
    }
    
    @PostMapping("postJson")
    @ResponseBody
    public Map<String,Object> postJson(@RequestBody User user, String key){
        Map<String,Object> map = new HashMap<String,Object>();
        String cookie = request.getHeader("cookie");
        map.put("cookie", cookie);
        map.put("key", key);
        if(user == null)
            return map;
        if(user.getId() != null)
            map.put("id", user.getId());
        if(user.getUsername() != null)
            map.put("username", user.getUsername());
        return map;
    }
    
    @RequestMapping("retmsg")
    @ResponseBody
    public void retmsg(){
        response.setContentType("application/json");
        try(OutputStream ros = response.getOutputStream()) {
            IOUtils.write("这是一个测试", ros, "UTF-8");
            ros.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
