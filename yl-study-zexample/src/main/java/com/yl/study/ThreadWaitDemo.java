package com.yl.study;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/8/22 0022.
 */
public class ThreadWaitDemo {

    /*private static int c = 1;
    private static Thread t = new Thread(() -> {
        c = 2;
        System.out.println("======");
        System.out.println(c);
    });

    static {
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello");
    }*/


    static {
        //线程类使用一次推荐使用匿名内部类
        Thread thread = new Thread() {
            //启动新线程将website属性进行赋值
            @Override
            public void run() {
                System.out.println("进入run()方法");
                System.out.println(website);
                website = "www.huya.com";
                System.out.println("退出run()方法");
            }
        };
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //定义一个静态的变量,设置初始值
    private static String website;

    /**
     * 线程会死锁，
     * 因为在static代码块中的线程要等类初始化完成才能获取website，但是类初始的线程（join）又在等static代码块中的线程
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ThreadWaitDemo.website + "=====");
    }


}
