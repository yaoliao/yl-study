package com.yl.study.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * Created by DELL_yaol on 2017/10/27.
 */
@SolrDocument(solrCoreName = "collection1")
public class Student implements Serializable{

    private static final long serialVersionUID = 119919718962648088L;

    @Field("id")
    private Integer id;

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("mobile")
    private String mobile;

    @Field("description")
    private String description;

    @Field("position")
    private Integer position;



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
}
