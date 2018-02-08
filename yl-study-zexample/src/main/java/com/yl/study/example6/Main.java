package com.yl.study.example6;

/**
 * Created by DELL on 2017/12/11.
 */
public class Main {

    public static void main(String[] args) {

        MyEnum.MY_ENUM.method1(2);

        MyEnum.MY_ENUM_1.newMethod(3);


        String content = MyEnum.MY_ENUM_1.getContent();
        System.out.println("content : " + content);

        String name = MyEnum.MY_ENUM.name();
        System.out.println("name====  " + name);

        /*try {
            Class<?> aClass = Thread.currentThread().getClass().getClassLoader().loadClass(MyEnum.class.getName());
            ClassLoader classLoader = MyEnum.class.getClassLoader();

            

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }

}
