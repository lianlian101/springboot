package com.test.springboot.entity.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Parent implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private int age;
    
    @JsonUnwrapped
    private Name name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parent [age=" + age + ", name=" + name + "]";
    }

}
