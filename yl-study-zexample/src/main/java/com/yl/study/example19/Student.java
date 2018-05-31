package com.yl.study.example19;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class Student {

    private String name;

    private int age;

    private ClassEnum classEnum;

    public Student() {
    }

    public Student(String name, int age, ClassEnum classEnum) {
        this.name = name;
        this.age = age;
        this.classEnum = classEnum;
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

    public ClassEnum getClassEnum() {
        return classEnum;
    }

    public void setClassEnum(ClassEnum classEnum) {
        this.classEnum = classEnum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classEnum=" + classEnum +
                '}';
    }
}
