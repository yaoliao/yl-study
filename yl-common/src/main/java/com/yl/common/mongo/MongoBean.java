package com.yl.common.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by DELL on 2018/2/2.
 */
public class MongoBean implements Cloneable  {

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            MongoKit.INSTANCE.error("MongoBean.class", e.getMessage());
        }
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Map toMap() {
        return MongoKit.INSTANCE.toMap(this);
    }

    public JSONObject toJSONObject() {
        return (JSONObject) JSON.toJSON(this);
    }

    public String toJSONString() {
        return  JSON.toJSONString(this);
    }

}
