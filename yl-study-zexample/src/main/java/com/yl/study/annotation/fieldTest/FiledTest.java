package com.yl.study.annotation.fieldTest;

import java.lang.reflect.Field;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 22:30
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class FiledTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        MyClass myClass = new MyClassImpl("tom");
        MyStudent myStudent = new MyStudent(myClass);
        myStudent.studentSay();

        Field field = MyStudent.class.getDeclaredField("myClass");
        field.setAccessible(true);
        field.set(myStudent, new MyClassImpl("Jack"));
        myStudent.studentSay();

        StudentHandler studentHandler = new StudentHandler(myClass);
        MyClass proxy = studentHandler.getProxy();
        field.set(myStudent, proxy);
        myStudent.studentSay();

        MyClass myClass1 = StudentHandler.get(myClass);
        field.set(myStudent, myClass1);
        myStudent.studentSay();


        System.out.println(MyClassImpl.class.isAssignableFrom(MyClassImpl.class));


    }

}
