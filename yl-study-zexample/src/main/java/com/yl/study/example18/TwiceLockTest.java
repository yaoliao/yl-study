package com.yl.study.example18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
public class TwiceLockTest {

    static TwiceLock lock = new TwiceLock();
    private static int num = 0;

    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(), String.valueOf(num++));
            list.add(thread);
        }

        list.forEach(Thread::start);


    }


    static class Worker implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                lock.lock();
                try {
                    System.out.println("我是线程 " + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
