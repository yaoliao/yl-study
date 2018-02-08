package com.yl.study.mongo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * Created by DELL on 2018/2/8.
 */
public class AggregationBean {

    private Integer count; //聚合数量

    @JSONField(name = "_id")
    private Map<String, String> id; // 聚合条件

    public Map<String, String> getId() {
        return id;
    }

    public void setId(Map<String, String> id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
