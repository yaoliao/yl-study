package com.yl.study.example17;

/**
 * Created by Administrator on 2018/5/15 0015.
 */
public class MyTestBean {

    private MyBeanInterface myBeanInterface;

    private String name;

    public void say(String name) {
        System.out.println(name + " oldName : " + this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyBeanInterface getMyBeanInterface() {
        return myBeanInterface;
    }

    public void setMyBeanInterface(MyBeanInterface myBeanInterface) {
        this.myBeanInterface = myBeanInterface;
    }
}
