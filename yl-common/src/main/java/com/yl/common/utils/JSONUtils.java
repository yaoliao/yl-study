package com.yl.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author DELL
 * @date 2017/10/26
 */
public class JSONUtils {


    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }


    /**
     * 多级泛型转化可以用 TypeReference
     * <p>
     * List<String> list2 = JSON.parseObject(jsonString,new TypeReference<List<String>>(){});
     * or
     * List<Map<String,Object>> list2 = JSON.parseObject(jsonString,new TypeReference<List<Map<String,Object>>>(){});
     * </p>
     *
     * @param json  json字符串
     * @param clazz 需要转化的类
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, TypeReference<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        return JSONObject.parseObject(jsonString, clazz);
    }


    public static String toJSONWithArray(Object object) {
        return JSONArray.toJSONString(object);
    }


}
