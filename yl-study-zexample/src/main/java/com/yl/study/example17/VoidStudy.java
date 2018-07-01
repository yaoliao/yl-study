package com.yl.study.example17;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
public class VoidStudy {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        Future<Void> future = pool.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                System.out.println("Say someThing .....");
                return null;
            }
        });



    }

}
