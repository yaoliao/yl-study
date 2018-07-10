package com.yl.study;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/7/6 0006.
 * <p>
 * 两个线程交替打印奇偶数
 */
public class TwoThread {

    private int number = 1;

    private volatile boolean flag = false;
//    private boolean flag = false;

    public static void main(String[] args) {

        TwoThread twoThread = new TwoThread();
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t = new Thread(new Run(twoThread, countDownLatch), "t");
        Thread t1 = new Thread(new Run1(twoThread, countDownLatch), "t1");

        t.start();
        t1.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结束了。。。。。。。。。。。");

    }


    public static class Run implements Runnable {

        private TwoThread twoThread;
        private CountDownLatch countDownLatch;

        Run(TwoThread twoThread, CountDownLatch countDownLatch) {
            this.twoThread = twoThread;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            while (twoThread.number <= 100) {

                if (twoThread.flag) {
                    System.out.println(twoThread.number + "   " + Thread.currentThread().getName() + "----------------");
                    twoThread.number++;
                    twoThread.flag = !twoThread.flag;
                }
            }

            countDownLatch.countDown();

        }

    }

    public static class Run1 implements Runnable {

        private TwoThread twoThread;
        private CountDownLatch countDownLatch;

        public Run1(TwoThread twoThread, CountDownLatch countDownLatch) {
            this.twoThread = twoThread;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            while (twoThread.number <= 100) {

                if (!twoThread.flag) {
                    System.out.println(twoThread.number + "   " + Thread.currentThread().getName());
                    twoThread.number++;
                    twoThread.flag = !twoThread.flag;
                }
            }

            countDownLatch.countDown();

        }

    }


}
