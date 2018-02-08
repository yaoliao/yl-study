package com.yl.study.mongo.model;

import com.yl.common.mongo.MongoBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 2018/2/6.
 */
public class MongoStudent extends MongoBean implements Serializable {
    private static final long serialVersionUID = 7262551171706201367L;

    private String name;
    private Integer age;
    private String des;
    private Date lastModifyTime;

    public MongoStudent() {
    }

    public MongoStudent(String name, Integer age, String des, Date lastModifyTime) {
        this.name = name;
        this.age = age;
        this.des = des;
        this.lastModifyTime = lastModifyTime;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
