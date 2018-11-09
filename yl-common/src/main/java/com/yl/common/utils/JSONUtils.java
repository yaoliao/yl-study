package com.yl.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yl.common.utils.serialize.fastjson.FastJsonSerializer;
import com.yl.common.utils.serialize.kryo.KryoSerializer;

import java.util.*;

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


    public static void main(String[] args) {

        /*Bo bo = new Bo();
        bo.setB(12L);
        bo.setA(11L);
        String string = toJSONString(bo);
        System.out.println(string);
        Bo bo1 = toObject(string, new TypeReference<Bo>() {
        });
        System.out.println(bo1.getA() == null ? "空了" : bo1.getA());

        System.out.println(bo.getA() + "=================");*/

//        String[] errorStr = {null};
//        String s = errorStr[0];
//        System.out.println(Optional.ofNullable(s).orElse("1"));

        List<Map<Integer, List<String>>> list = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("aaaa");
        HashMap<Integer, List<String>> map = new HashMap<>();
        map.put(1, strings);
        list.add(map);

        FastJsonSerializer fast = new FastJsonSerializer();
        byte[] serialize = fast.serialize(list);
        List deserialize = fast.deserialize(serialize, List.class);

        KryoSerializer kryo = new KryoSerializer();
        byte[] bytes = kryo.serialize(list);
        List<Map<Integer, List<String>>> deserialize1 = kryo.deserialize(bytes, ArrayList.class);



    }

    static class Bo {
        private transient Long a;
        private Long b;

        public Bo() {
        }

        public Bo(Long a, Long b) {
            this.a = a;
            this.b = b;
        }

        public Long getA() {
            return a;
        }

        public void setA(Long a) {
            this.a = a;
        }

        public Long getB() {
            return b;
        }

        public void setB(Long b) {
            this.b = b;
        }
    }

}
