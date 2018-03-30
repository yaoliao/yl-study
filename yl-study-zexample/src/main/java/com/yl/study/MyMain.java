package com.yl.study;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class MyMain {

    public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<>(8);

        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        map.put("dd", "44");
        map.put("ee", "55");

        String ee = map.get("ee");

        map.forEach((k, v) -> System.out.println(k + " --- " + v));

    }

}
