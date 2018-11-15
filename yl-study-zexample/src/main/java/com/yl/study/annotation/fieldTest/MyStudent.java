package com.yl.study.annotation.fieldTest;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 22:20
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyStudent {

    private MyClass myClass;

    public MyStudent() {
    }

    public MyStudent(MyClass myClass) {
        this.myClass = myClass;
    }

    public void studentSay() {
        System.out.println("----------   MyStudent   say   ------------------");
        myClass.say();
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }
}
