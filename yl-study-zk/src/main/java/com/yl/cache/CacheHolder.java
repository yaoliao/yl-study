package com.yl.cache;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 18:46
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class CacheHolder {

    private static Map<String, String> map = new ConcurrentHashMap<>();

    private CacheHolder() {
    }

    public static void put(String key, String value) {
        map.put(key, value);
    }

    public static String get(String key) {
        return map.get(key);
    }

    public static Map<String, String> getAll() {
        return Collections.unmodifiableMap(map);
    }

    public static void clear() {
        map.clear();
    }
}

