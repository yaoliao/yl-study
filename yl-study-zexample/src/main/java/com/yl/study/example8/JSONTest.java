package com.yl.study.example8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2018/1/3.
 */
public class JSONTest {


    public static void main(String[] args) {

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("1","11");
        map1.put("2","22");
        list.add(map);
        list.add(map1);

        String string = JSON.toJSONString(list);

        List<Map<String, Object>> ll = JSON.parseObject(string, new TypeReference<List<Map<String, Object>>>() {
        });



    }

}
