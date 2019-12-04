package com.test.springboot.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springboot.general.pojo.RequestParam;
import com.test.springboot.general.util.RequestParamsUtil;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RequestParamsUtil requestDatasUtil;
    
    /**
     * 2019年4月13日 测试请求头
     * @param request
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
	public RequestParam testReqHead(HttpServletRequest request) {
        RequestParam datas = null;
        try {
            datas = requestDatasUtil.getParams(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return datas;
	}
	
    /**
     * 日期：2019年10月25日
     * 作者：zhb
     * 说明：PrintWriter测试
     * 
     * @param response
     * @return
     */
    @RequestMapping("/pw")
    public String rw(int data, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("fail");
            if(data == 0){
                out.println("不用跳转");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/printwriter/index_2";
    }
    
    @RequestMapping("/fun")
    @ResponseBody
    public String fun(){
        try {
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
    
}
