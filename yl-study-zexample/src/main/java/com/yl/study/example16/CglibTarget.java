package com.yl.study.example16;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public class CglibTarget {

    public String go(String aa) {
        System.out.println("gogogogogo  " + aa);
        return aa;
    }

    public String back(String aa) {
        System.out.println("backbackback  " + aa);
        return aa;
    }

}
