package com.yl.study;

import com.vip.vjtools.vjkit.mapper.BeanMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/8/31 0031
 * @time 下午 17:10
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class BeanMapperTest {

    public static void main(String[] args) {

        /*List<String> list = new ArrayList<>();

        list.add("11");
        list.add("22");

        B b = new B();
        b.setList(list);

        A a = new A("test");
        a.setB(b);

        System.out.println(a.getB().getClass().toString());

        //BeanUtils.copyProperties(a, a1);
        *//**
         * 这是深拷贝
         *//*
        A a1 = BeanMapper.map(a, A.class);

        //List<String> list1 = a1.getB().getList();
        System.out.println(a1.getB().getClass().toString());

        List<String> list1 = a1.getB().getList();
        list1.add(0, "new1111");
        System.out.println(list.get(0));
        System.out.println(list1.get(0));*/


        List<String> list = new ArrayList<>();

        list.add("11");
        list.add("22");

        B b = new B();
        b.setList(list);

        A a = new A("test");
        a.setB(b);

        System.out.println(a.getB().getClass().toString());

        A a1 = new A();

        /**
         * 这是浅拷贝
         */
        BeanUtils.copyProperties(a, a1);
//        A a1 = BeanMapper.map(a, A.class);

        //List<String> list1 = a1.getB().getList();
        System.out.println(a1.getB().getClass().toString());

        List<String> list1 = a1.getB().getList();
        list1.add(0, "new1111");
        System.out.println(list.get(0));
        System.out.println(list1.get(0));

    }


    public static class A {

        private String a;

        private B b;

        public A() {
        }

        public A(String a) {
            this.a = a;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    public static class B {

        private List<String> list;

        public B() {
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
