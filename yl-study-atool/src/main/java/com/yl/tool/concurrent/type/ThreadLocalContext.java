package com.yl.tool.concurrent.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/8/31 0031
 * @time 下午 13:54
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ThreadLocalContext {

    private final static ThreadLocal<Map<String, Object>> local = ThreadLocal.withInitial(HashMap::new);

    private ThreadLocalContext() {
    }

    public static void put(String key, Object value) {
        local.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) local.get().get(key);
    }

    public static void clear() {
        local.get().clear();
    }


}
