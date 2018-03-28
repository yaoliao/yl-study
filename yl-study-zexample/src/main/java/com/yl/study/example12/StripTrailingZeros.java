package com.yl.study.example12;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by DELL on 2018/3/23.
 */
public class StripTrailingZeros {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /**
         * 小数点末尾去0  23.20 --> 23.2  23.00 --> 23
         */
        String string = new BigDecimal(Double.toString(60.020D)).stripTrailingZeros().toPlainString();
        String string1 = new BigDecimal("60.00").stripTrailingZeros().toPlainString();

        System.out.println(string);
        System.out.println(string1);

        /**
         * 保留两位小数
         */
        String price = String.format("%.2f", 12345678.21D / 10000);
        System.out.println(price);

        Double d = 123.020D;
        Double d1 = 123.000D;
        System.out.println(Double.toString(d));


        /**
         * hashMap中table被transient修饰，不会被序列化(transient Node<K,V>[] table;)
         * hashMap中有readObject 和 writeObject 两个方法，在序列化时会调用这两个方法去序列化
         * 使用上述两个方法序列化的原因：不同的系统中计算出的key的hash值是不同的，所以不能直接序列化，而是在writeObject中重新hash后放入table中
         */
        Map<String, String> map = new HashMap<>(16);
        map.put("11", "aaa");
        map.put("22", "bbb");
        map.put("33", "ccc");

        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);

        Map<String, String> map1 = deepClone(map);
        map1.forEach((k, v) -> System.out.println(k + " -- " + v));

    }

    @SuppressWarnings("unchecked")
    public static <T> T deepClone(T t) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(outputStream);
        oo.writeObject(t);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream in = new ObjectInputStream(inputStream);
        Object object = in.readObject();
        return (T) object;
    }

}
