package com.yl.study.example16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class FunctionStudy {

    public static void main(String[] args) {


        say(aa -> aa.getName() + aa.getAge());


    }


    public static String say(Function<AA, String> function) {
        AA aa = new AA();
        aa.setName("Tom");
        aa.setAge(11);
        String apply = function.apply(aa);
        System.out.println(apply);
        return apply;
    }

    public static class AA {

        private String name;
        private int age;

        public AA() {
        }

        public AA(String name, int age) {
            this.name = name;
            this.age = age;
        }

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
    }
}
