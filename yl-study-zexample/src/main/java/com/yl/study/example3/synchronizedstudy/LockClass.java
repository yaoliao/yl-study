package com.yl.study.example3.synchronizedstudy;

import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 * @date 2017/11/10
 */
public class LockClass {

    public void method() {

        synchronized (LockClass.class) {
            System.out.println("LockObject in ............." + Thread.currentThread().getName());
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("LockObject 我在循环。。。" + Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) {

        final LockObject lockObject = new LockObject();
        final LockObject lockObject1 = new LockObject();


        //=================================================

        /**
         * 类锁，只有一个线程会运行
         */

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                lockObject.method();
            }
        }, "我是阻塞一号").start();


        //上面线程阻塞，这个执行不到
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockObject.method();
            }
        }, "我是阻塞二号").start();*/


        //=================================================

        /**
         * 类锁，只有一个线程会运行
         *
         */

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockObject.method();
            }
        }, "我是阻塞一号").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockObject1.method();
            }
        }, "我是阻塞二号").start();


    }

}
