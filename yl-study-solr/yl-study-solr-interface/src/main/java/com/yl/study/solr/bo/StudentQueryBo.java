package com.yl.study.solr.bo;

import java.io.Serializable;

/**
 * 用于查询的查询条件Bo
 *
 * @author DELL
 * @date 2017/10/27
 */
public class StudentQueryBo implements Serializable {

    private static final long serialVersionUID = 5634764330277347924L;


    private Integer id;

    private String name;

    private Integer age;

    private String mobile;

    private String description;

    private Integer position;

    private Integer minAge;

    private Integer maxAge;

    private Boolean hasPosition;


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

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Boolean getHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(Boolean hasPosition) {
        this.hasPosition = hasPosition;
    }
}
