package com.yl.study.example11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 2018/3/15.
 */
public class Singleton {

    private static final Singleton singleton = new Singleton();

//    private static final List<String> list = new ArrayList<>();
    private static final List<String> list = Collections.synchronizedList(new ArrayList<>());


    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

    public void setList(String i) {
        list.add(i);
    }

    public List<String> get() {
        return list;
    }


}
