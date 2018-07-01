package com.yl.study.java8Study;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/6/25 0025.
 */
public class LambdaStudy {

    public static void main(String[] args) {

        /*ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));

        ThreadLocal<SimpleDateFormat> threadLocal1 = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return super.initialValue();
            }
        };


        Runnable hello = () -> System.out.println("hello word");

        JButton button = new JButton();
        button.addActionListener(event -> System.out.println(event.getActionCommand()));

        List<String> list = Arrays.asList("Tom", "Jack", "Rose", "Tomas");
        long count = list.stream().filter(value -> {
            System.out.println(value);
            return value.startsWith("T");
        }).count();

        List<String> strings = Stream.of("a", "b", "c").collect(Collectors.toList());

        List<Integer> integerList = Stream.of("1", "3", "5").map(Integer::valueOf).collect(Collectors.toList());

        Integer integer = Stream.of(123, 254, 12, 45).max(Comparator.comparing(val -> val)).get();
        System.out.println(integer);


        List<String> list1 = Arrays.asList("ertf", "sd", "dfs", "a", "wee");
        Collections.sort(list1, Comparator.comparingInt(String::length));
        list1.forEach(System.out::println);


        String s = list1.stream().mapToInt(String::length).summaryStatistics().toString();
        String s1 = list1.stream().mapToInt(value -> value.length()).summaryStatistics().toString();
        System.out.println(s);

        String collect = list1.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect);

        Map<Integer, List<String>> collect1 = list1.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(collect1);


//        Stream.of(Arrays.asList("a","c")).flatMap()
        System.out.println("===============");
        List<Album> albums = Arrays.asList(new Album("yesterday_专辑1", new Artist("保罗", 23)), new Album("yesterday_专辑2", new Artist("保罗", 23)),
                new Album("comeTogether_专辑1", new Artist("约翰列侬", 22)));
        *//*Stream<Album> albumStream = Stream.of(new Album("yesterday_专辑1", new Artist("保罗", 23)), new Album("yesterday_专辑2", new Artist("保罗", 23)),
                new Album("comeTogether_专辑1", new Artist("约翰列侬", 22)));*//*

        Map<Artist, List<Album>> collect2 = albums.stream().collect(Collectors.groupingBy(Album::getMainMusician));
        String s2 = JSON.toJSONString(collect2);
        Map<Artist, Set<Album>> collect3 = albums.stream().collect(Collectors.groupingBy(Album::getMainMusician, Collectors.toSet()));
        Map<Artist, List<Artist>> collect4 = albums.stream().collect(Collectors.groupingBy(Album::getMainMusician, Collectors.mapping(Album::getMainMusician, Collectors.toList())));
        Map<String, List<String>> collect5 = albums.stream().collect(Collectors.groupingBy(album -> album.getMainMusician().getName(), Collectors.mapping(Album::getName, Collectors.toList())));
        System.out.println(collect2);
        System.out.println(collect3);
        System.out.println(collect4);
        System.out.println(collect5);


        StringJoiner joiner = new StringJoiner(",");
        String string = joiner.add("aa").add("bb").toString();


        List<String> collect6 = albums.parallelStream().map(Album::getMainMusician).map(Artist::getName).collect(Collectors.toList());*/




        Map<String, String> map = new HashMap<>();
        map.put("b","bb");
        map.put("a","aa");
        map.put("名字","阿西吧");
        map.put("c","cc");

        TreeMap treeMap = new TreeMap();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.forEach(e -> treeMap.put(e.getKey(), e.getValue()));
        System.out.println(entries);
        System.out.println(treeMap);


//        Map<String, String> map = new HashMap<>();
//        map.putIfAbsent("","");
//        map.computeIfAbsent()



    }

    static class Album {

        private String name;
        private Artist mainMusician;

        public Album(String name, Artist mainMusician) {
            this.name = name;
            this.mainMusician = mainMusician;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Artist getMainMusician() {
            return mainMusician;
        }

        public void setMainMusician(Artist mainMusician) {
            this.mainMusician = mainMusician;
        }

        @Override
        public String toString() {
            return "Album{" +
                    "name='" + name + '\'' +
                    ", mainMusician=" + mainMusician +
                    '}';
        }
    }

    static class Artist {

        public Artist(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Artist{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
