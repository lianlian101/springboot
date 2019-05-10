package com.test.springboot.general.pojo;

import java.io.Serializable;

public class RequestParam implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     ** 参数类型 1:json, 2:键值对, 默认值为1(json)
     */
    private int code = 1;
    /**
     ** 信息
     */
    private String msg;
    /**
     ** 数据
     */
    private Object data;
   
    public RequestParam() {
        super();
    }
    public RequestParam(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Datas [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
