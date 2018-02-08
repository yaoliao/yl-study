package com.yl.study.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/12/28.
 */
public class StudentPojo implements Serializable {

    private static final long serialVersionUID = -8793614786780826204L;

    private Integer id;

    private String name;

    private Integer age;

    private String mobile;

    private String description;

    private Integer position;

    private List<CoursePojo> coursePojos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<CoursePojo> getCoursePojos() {
        return coursePojos;
    }

    public void setCoursePojos(List<CoursePojo> coursePojos) {
        this.coursePojos = coursePojos;
    }
}
