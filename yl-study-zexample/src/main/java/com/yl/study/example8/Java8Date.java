package com.yl.study.example8;

import java.time.LocalDateTime;

/**
 * Created by DELL on 2018/1/8.
 */
public class Java8Date implements MyInterface {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

    }

    public void say(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

}
