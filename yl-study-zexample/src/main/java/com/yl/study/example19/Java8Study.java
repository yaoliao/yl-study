package com.yl.study.example19;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class Java8Study {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(new Student("Tom", 22, ClassEnum.CLASS_1),
                new Student("Jack", 20, ClassEnum.CLASS_2),
                new Student("Rose", 18, ClassEnum.CLASS_1),
                new Student("Harry Potter", 22, ClassEnum.CLASS_3),
                new Student("Rowen", 22, ClassEnum.CLASS_3),
                new Student("Hermione", 21, ClassEnum.CLASS_3));

        /**
         * 分组 教室-Student
         */
        Map<ClassEnum, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getClassEnum));

        collect.forEach((k, v) -> {
            System.out.println(k.getValue() + "----");
            v.forEach(student -> System.out.println(student.getName()));
        });

        System.out.println("====================");

        /**
         * 分组 教室-名字
         */
        Map<Integer, List<String>> map = students.stream().collect(Collectors.groupingBy(new GroupFunction(), Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(map);

        System.out.println("====================");

        /**
         * 分组 教室-数量
         */
        Map<ClassEnum, Long> map1 = students.stream().collect(Collectors.groupingBy(Student::getClassEnum, Collectors.counting()));
        System.out.println(map1);

    }

    static class GroupFunction implements Function<Student, Integer> {

        @Override
        public Integer apply(Student student) {
            return student.getClassEnum() != null ? student.getClassEnum().getKey() : 99;
        }
    }

}
