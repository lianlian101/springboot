package com.test.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springboot.pojo.RequestParam;
import com.test.springboot.util.RequestParamsUtil;

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
	
}
