package com.yl.study;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
public class LambdaTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        final String a = "aa";
//        lam(s -> s = "bb");
        /*final String[] a = {"aa"};
        lam(s -> a[0] = "bb");*/
       /* System.out.println(StringUtils.isBlank(""));
        System.out.println("".length());*/


        /*List<String> list = new ArrayList<>();

        B b = new B();
        b.setList(list);

        A a = new A("test");
        a.setB(b);

        A a1 = new A("test1");
        BeanUtils.copyProperties(a, a1);

        List<String> list1 = a1.getB().getList();
        System.out.println();*/
        /*Integer num = 1;
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> say(num));
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> say1(num));
        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("啊呀异步失败了。。");
                throw new RuntimeException("啊呀异步失败了。。  原因: " + throwable.getMessage());
            } else {
                System.out.println("异步成功了。。" + result);
            }
        });*/

        /*try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw e;
        }*/

        /*System.out.println("===========");

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        /*HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("String", null);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("aa", null);*/


        /*List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 6, 17, 18, 19, 20);

        ArrayList<Integer> list1 = Lists.newArrayList();

        list.parallelStream().forEach(list1::add);
        list.parallelStream().forEachOrdered(System.out::print);*/

        //131514121171820191763542191086
        //131872015196179141012611385214

        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("12345");
            return 233333;
        });

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("12345");
            throw new RuntimeException("111");
        });

        CompletableFuture<Void> future2 = CompletableFuture.allOf(future1, future).whenComplete((r, e) -> {
            if (r != null) System.out.println(r);
            if (e != null) System.out.println(e.getMessage());
        });

        future2.join();*/

        Integer i = null;
        if(i > 0){
            System.out.println("111");
        }else {
            System.out.println("2222");
        }



    }

    public static void say(Integer num) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1----2-----3------4------");
        System.out.println(num);
        num++;
        System.out.println(num);
        throw new RuntimeException("这是异步的异常");
    }

    public static Integer say1(Integer num) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1----2-----3------4------");
        System.out.println(num);
        num++;
        System.out.println(num);
        return num;
    }

    private static <T> Class get(T t) {
        Class<? extends Object> aClass = t.getClass();
        Field[] fields = t.getClass().getFields();
        Type genericType = fields[0].getGenericType();
        return aClass;
    }

    private static void lam(Consumer<String> consumer) {
        consumer.accept(null);
    }

    private static void convert(final A a) {
        a.setA("bb");
    }

    static class A {

        private String a;

        private B b;

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

    static class B {

        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

}
