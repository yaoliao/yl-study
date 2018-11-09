package com.yl.study;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2018/10/26 0026
 * @time 上午 10:36
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class WaitTest {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                lock.notify();
            }
        }).start();

        synchronized (lock) {
            lock.wait();
            System.out.println("==============");
        }

    }
}
