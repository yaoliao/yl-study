package com.yl.study.example17;

/**
 * Created by Administrator on 2018/5/15 0015.
 */
public class MyTestBean {

    private String name;
    private Integer age;

    public void say() {
        System.out.println(name + "  : " + age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
