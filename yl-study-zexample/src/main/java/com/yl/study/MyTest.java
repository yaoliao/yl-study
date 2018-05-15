package com.yl.study;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
public class MyTest {

    static class A{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    static class B extends A {
        private String b;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    static class C {
        private A a;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    private static A buidA(A a) {
        a.setA("我是A");
        return a;
    }

    public static void main(String[] args) {
        /*C c = new C();
        B b =new B();
        b.setB("我是B");
        System.out.println(buidA(b).getClass().getName());
        c.setA(buidA(b));
        System.out.println(JSON.toJSONString(c));*/


        int[] ints = new int[]{66,4,5,1,2,4,1,2,1};
        Set<Integer> set = new HashSet<>();

        for (int i : ints) {
            set.add(i);
        }

        Integer[] integers = set.toArray(new Integer[]{});
        for (Integer i : integers) {
            System.out.println(i);
        }


    }




}
