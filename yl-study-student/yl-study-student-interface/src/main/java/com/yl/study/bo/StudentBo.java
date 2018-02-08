package com.yl.study.bo;

import com.yl.common.utils.BeanUtil;
import com.yl.study.enums.PositionEnum;

import java.io.Serializable;

/**
 * @author DELL
 * @date 2017/10/26
 */
public class StudentBo implements Serializable, BeanUtil.ConversionCustomizble {

    private static final long serialVersionUID = -5093580251090490675L;

    private Integer id;

    private String name;

    private Integer age;

    private String mobile;

    private String description;

    private PositionEnum position;


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

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    @Override
    public void convertOthers(Object o) {
        Object position = BeanUtil.getPropValue(o, "position");
        if (position != null && position instanceof Integer) {
            this.setPosition(PositionEnum.valueOf((Integer) position));
        }
    }

    @Override
    public String toString() {
        return "StudentBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                ", position=" + position +
                '}';
    }
}
