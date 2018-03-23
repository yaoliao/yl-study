package com.yl.study.example11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by DELL on 2018/3/15.
 */
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {

        final Integer num = 10;

        Singleton instance = Singleton.getInstance();

        CyclicBarrier c = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("================");
            }
        });

        c.reset();

        CountDownLatch countDownLatch = new CountDownLatch(num);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "  start");
                        c.await();
                        for (int j = 0; j < 10; j++) {
                            instance.setList(Thread.currentThread().getName() + "  " + j);
                        }
                        System.out.println(Thread.currentThread().getName() + "  end");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    countDownLatch.countDown();

                }
            }, "Thread -- " + i);
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }

        countDownLatch.await();
        List<String> list = instance.get();
        for (String s : list) {
            System.out.println(s);
        }

    }

}
