package com.yl.study.example7;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by DELL on 2017/12/21.
 */
public class MyExcotor2 {

    private static LinkedBlockingQueue<Integer> custIDQueue = new LinkedBlockingQueue();

    private static LinkedBlockingQueue<Integer> workQueue = new LinkedBlockingQueue<>();

    private static final Integer NUM = 10;

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(NUM);

        Long start = System.currentTimeMillis();

        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        for (Integer i : list) {
            boolean offer = custIDQueue.offer(i);
            if (!offer) {
                throw new RuntimeException("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        }

        ExecutorService t1 = Executors.newFixedThreadPool(NUM);
        ExecutorService t2 = Executors.newFixedThreadPool(5);

        Long befor = System.currentTimeMillis();
        Long end1 = befor - start;
        System.out.println("end1 ========== " + end1);
        System.out.println(custIDQueue.size());


        /*for (int i = 0; i < NUM; i++) {
            t1.execute(new Runnable() {
                @Override
                public void run() {
                    while (!custIDQueue.isEmpty()) {
                        Integer poll = custIDQueue.poll();
                        if (poll != null) {
                            boolean offer = workQueue.offer(poll);
                            if (!offer) {
                                throw new RuntimeException("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                            }
                            try {
                                TimeUnit.MILLISECONDS.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            throw new RuntimeException("cccccccccccccccccccccccc");
                        }
                    }
                    latch.countDown();
                }
            });
        }*/


        for (int i = 0; i < NUM; i++) {
            Future<Integer> f = t1.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        while (!custIDQueue.isEmpty()) {
                            Integer poll = custIDQueue.poll();
                            if (poll != null) {
                                boolean offer = workQueue.offer(poll);
                                if (!offer) {
                                    throw new RuntimeException("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                                }
                                try {
                                    TimeUnit.MILLISECONDS.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
    //                            throw new RuntimeException("cccccccccccccccccccccccc");
                            }
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        return -1;
                    }
                    return 0;
                }
            });
            try {
                list1.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (Integer i : list1) {
            if(i < 0){
                System.out.println("报错了。。。。");
            }
        }

        /*try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Long after = System.currentTimeMillis();
        Long end = after - befor;
        System.out.println("===============   " + end);
        System.out.println("workQueue size " + workQueue.size());
        t1.shutdown();


    }

}
