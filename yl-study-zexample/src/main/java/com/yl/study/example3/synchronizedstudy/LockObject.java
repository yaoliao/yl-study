package com.yl.study.example3.synchronizedstudy;

import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 * @date 2017/11/10
 */
public class LockObject {

    private static final Object OBJECT = new Object();
    private static final Object OBJECT1 = new Object();


    public void method() {

        synchronized (OBJECT) {

            System.out.println("LockObject in ............." + Thread.currentThread().getName());
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("LockObject 我在循环。。。" + Thread.currentThread().getName());
            }
        }
    }

    public void method1() {

        synchronized (OBJECT1) {

            System.out.println("LockObject in ............." + Thread.currentThread().getName());
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(4);
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
         * 这里不管OBJECT是否为static，都只有一个线程会运行
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
         * 静态成员变量不属于任何对象,它被该类的所有对象所共享 ---->> 这句话很重要
         * 如果 OBJECT 是static修饰的，则这个成员变量在--方法区(被所有的线程共享)--中只会有一个，两个线程只有一个可以获取锁，
         * 所以只有一个线程可以运行(这是就相当于类锁)
         * 如果 不是 static，则这个成员变量在每次new 这个类时都会分配一个新的，所以两个线程都可以运行
         */

        /*new Thread(new Runnable() {
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
        }, "我是阻塞二号").start();*/


        //=================================================

        /**
         * 这里和上面一样，分成员变量是静态还是非静态
         * 如果是 OBJECT 和 OBJECT1 都是静态,则只有一个会运行（原理同上，用static修饰的成员变量，相当于是类锁）
         * 如果一个静态，一个非静态，或者两个都非静态，则都不阻塞
         *
         */

        new Thread(() -> lockObject.method(), "我是阻塞一号").start();

        new Thread(() -> lockObject.method1(), "我是阻塞二号").start();


    }

}
