package com.yl.study.annotation.fieldTest;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 22:20
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyClassImpl implements MyClass {

    private String name;

    public MyClassImpl() {
    }

    public MyClassImpl(String name) {
        this.name = name;
    }

    public String say() {
        System.out.println("===============   MyClassImpl  say   =============================");
        System.out.println("name : " + name);
        return "MyClassImpl#say";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
