package com.yl.study;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class MyMain {

    public static void main(String[] args) {

       /* Map<String, String> map = new LinkedHashMap<>(8);

        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        map.put("dd", "44");
        map.put("ee", "55");

        String ee = map.get("ee");

        map.forEach((k, v) -> System.out.println(k + " --- " + v));


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));

        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.toBinaryString(2147483647));


        *//**
         * 利用zookeeper实现的分布式锁，强啊
         *//*
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
        }*/

        List<T> ts = Arrays.asList(new T("a", 2), new T("1", 1), new T("b", 2), new T("a", 1), new T("1", 2), new T("b", 1));
        List<T> collect = ts.stream().sorted(Comparator.comparing(T::getA).thenComparing(Comparator.comparing(T::getB))).collect(Collectors.toList());
        System.out.println(collect);

        List<T> collect1 = ts.stream().sorted(Comparator.comparing(T::getA).thenComparing(Comparator.comparing(T::getB).reversed())).collect(Collectors.toList());
        System.out.println(collect1);

        /*T a = new T("a", 1);
        Optional<String> s = Optional.ofNullable(a).map(T::getA);
        Optional<Optional<String>> s2 = Optional.ofNullable(a).map(T::getOps);
        Optional<String> s1 = Optional.ofNullable(a).flatMap(T::getOps);
        s1.orElseGet("tTTtt"::toLowerCase);*/

        /*T t = new T(null, 12);
        T tt = new T("afrdFDH", 12);

        String s3 = Optional.ofNullable(t).map(T::getA).map(String::toUpperCase).orElse("这是null。。。。。。");
        System.out.println(s3);

        Optional<String> s = Optional.ofNullable(tt).map(T::getA);
        s.map(String::toUpperCase).ifPresent(e -> System.out.println(e.toUpperCase()));

        String t1 = Optional.ofNullable(t).map(T::getA).filter(Objects::nonNull).orElse("空");
        System.out.println(t1);*/
    }

    static class T {
        private String a;
        private Integer b;

        private Optional<String> ops;

        public T() {
        }

        public T(String a, Integer b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public Optional<String> getOps() {
            return ops;
        }

        public void setOps(Optional<String> ops) {
            this.ops = ops;
        }

        @Override
        public String toString() {
            return "T{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    '}';
        }
    }

}
