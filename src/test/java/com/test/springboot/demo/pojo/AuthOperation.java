package com.test.springboot.demo.pojo;

import java.io.Serializable;

public class AuthOperation implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 一级
     */
    private String classificationName;

    /**
     * 二级
     */
    private String function;

    /**
     * 三级
     */
    private String functionChild;

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunctionChild() {
        return functionChild;
    }

    public void setFunctionChild(String functionChild) {
        this.functionChild = functionChild;
    }

    @Override
    public String toString() {
        return "AuthOperation [classificationName=" + classificationName + ", function=" + function + ", functionChild="
                + functionChild + "]";
    }

}
