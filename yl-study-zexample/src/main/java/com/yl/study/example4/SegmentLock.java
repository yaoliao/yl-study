package com.yl.study.example4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by DELL on 2017/12/14.
 */
public class SegmentLock {

    /**
     * 这里是本次试验的关键  配合 com.yl.study.cache.CacheTemplateService 使用
     */
    private static final Object[] LOCKS = new Object[]{new Object(), new Object(), new Object()};

    public void method(int i) {

        System.out.println(Thread.currentThread().getName() + "  method start =======");
        synchronized (LOCKS[i]) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "  method in lock ........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
//        System.out.println("method end =========");
    }

    public static void main(String[] args) {

        final SegmentLock segmentLock = new SegmentLock();
        final SegmentLock segmentLock1 = new SegmentLock();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            try {
                System.out.println("开始了。。。。");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread a = new Thread(() -> {
            try {
                cyclicBarrier.await();
                segmentLock.method(1);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }, "Thread AAAA");

        a.setPriority(Thread.MAX_PRIORITY);
        a.start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
                segmentLock.method(1);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }, "Thread BBBB").start();

    }

}
