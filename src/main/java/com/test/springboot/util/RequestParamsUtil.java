package com.test.springboot.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.test.springboot.pojo.RequestParam;

/**
 * 2019年4月13日 请求数据封装<只解析json格式和键值对格式的数据>
 * 
 * @author admin
 *
 */
@Component
public class RequestParamsUtil {

    /**
     * json 类型
     */
    private final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    /**
     * 2019年4月13日 获取请求参数
     * @param request
     * @return
     * @throws Exception
     */
    public RequestParam getParams(HttpServletRequest request) throws Exception {
        // 获取数据类型
        String contentType = request.getContentType();
        // 保存前端传过来的所有数据
        RequestParam datas = new RequestParam();
        if (null != contentType && CONTENT_TYPE_JSON.contains(contentType.toLowerCase())) {
            String params = getJsonData(request);
            if(null != params) {
                if(params.contains("&") || params.contains("=") || !params.contains("{") || !params.contains("}")) {
                    datas.setMsg("格式错误");
                }
                datas.setData(params);
            }
        } else {
            datas.setCode(2);
            Map<String, Object> map = getKeyValue(request);
            if(null != map) {
                datas.setData(map);
            }
        }
        return datas;
    }

    /**
     * 2019年4月13日 获取json数据
     * @param request
     * @return
     * @throws Exception 
     */
    public String getJsonData(HttpServletRequest request) throws Exception {
        // 读取请求数据
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String data = sb.toString();
        if(null == data || "".equals(data.trim())) {
            data = null;
        }
        return data;
    }
    
    /**
     * 2019年4月13日 获取键值对
     * @param request
     * @return
     */
    public Map<String,Object> getKeyValue(HttpServletRequest request) {
        // 保存参数和对应的值
        HashMap<String, Object> map = new HashMap<String, Object>();
        // key-value 形式
        // 获取所有的参数及其对应的值
        Map<String, String[]> params = request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = params.entrySet();
        for (Entry<String, String[]> entry : entrySet) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (null != values) {
                if (values.length > 1) {// 多个值
                    LinkedList<Object> list = new LinkedList<Object>();
                    for (int i = 0, len = values.length; i < len; i++) {
                        list.add(values[i]);
                    }
                    map.put(key, list);
                } else {// 一个值
                    String value = values[0];
                    if(null != value && !"".equals(value)) {
                        map.put(key, value);
                    }
                }
            }
        }
        if(map.isEmpty()) {
            map = null;
        }
        return map;
    }

}