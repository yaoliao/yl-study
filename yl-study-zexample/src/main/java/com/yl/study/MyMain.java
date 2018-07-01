package com.yl.study;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class MyMain {

    public static void main(String[] args) {

        /*Map<String, String> map = new LinkedHashMap<>(8);

        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        map.put("dd", "44");
        map.put("ee", "55");

        String ee = map.get("ee");

        map.forEach((k, v) -> System.out.println(k + " --- " + v));*/


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));

        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.toBinaryString(2147483647));


        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("").connectionTimeoutMs(5000).build();
        InterProcessMutex lock = new InterProcessMutex(client, "/InterProcessLock");

        try {
            lock.acquire();
        } catch (Exception e) {

        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }





        Map<String, List<String>> collect = Stream.of("tom", "jack", "tom", "rose").collect(Collectors.groupingBy(val -> val));
        System.out.println(collect);
        Map<String, Long> collect1 = Stream.of("tom", "jack", "tom", "rose").collect(Collectors.groupingBy(val -> val, Collectors.counting()));
        System.out.println(collect1);

    }

}
