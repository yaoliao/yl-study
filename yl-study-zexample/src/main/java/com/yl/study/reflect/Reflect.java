package com.yl.study.reflect;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 小新
 * @date 2018/9/6
 */
@Transactional
public class Reflect {

    private static int num = 0;

    public static void main(String[] args) {
        new Reflect().start();
    }

    public void start() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::doReport, 0, 1, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::inc, 0, 3, TimeUnit.SECONDS);
    }

    public void doReport() {
        try {
            Field field = Pojo.class.getDeclaredField("num");
            field.setAccessible(true);
            int o = (int) field.get(Pojo.class);
            num = o;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }

    public void inc() {
        Pojo.inc();
    }

}
