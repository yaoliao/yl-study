package com.yl.study.pojo;

import java.io.Serializable;

/**
 * Created by DELL on 2017/12/28.
 */
public class CoursePojo implements Serializable{

    private static final long serialVersionUID = 6759593353454785577L;

    private Integer ID;

    private String name;

    private Integer studentID;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }
}
