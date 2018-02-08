package com.yl.study.example7;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by DELL on 2017/12/21.
 */
public class MyExcotor {

    private static LinkedBlockingQueue custIDQueue = new LinkedBlockingQueue();

    private static LinkedBlockingQueue<Integer> workQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Long befor = System.currentTimeMillis();
                System.out.println("t1 before " + befor);
                while (!list.isEmpty()) {
                    int size = list.size();
                    Integer integer = list.get(size - 1);
                    list.remove(size - 1);
                    try {
                        TimeUnit.MILLISECONDS.sleep(20);
                        workQueue.put(integer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Long after = System.currentTimeMillis();
                Long end = after - befor;
                System.out.println("t1 use time ========= " + end);
            }
        }, "Thread 1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Long befor = System.currentTimeMillis();
                System.out.println("t1 before " + befor);
                while (t1.isAlive() || !list.isEmpty() || workQueue.size() > 0) {
                    Integer take = workQueue.poll();
                    if (take != null) {
                        list1.add(take);
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Long after = System.currentTimeMillis();
                Long end = after - befor;
                System.out.println("t2 use time ========= " + end);
                System.out.println("======  size  ==========  " + list1.size());
            }
        }, "Thread 2");

        t1.start();
        t2.start();






    }


}
