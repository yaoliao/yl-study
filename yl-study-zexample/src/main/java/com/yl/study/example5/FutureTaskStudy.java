package com.yl.study.example5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by DELL on 2017/11/20.
 */
public class FutureTaskStudy {

    private final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            // do something
            System.out.println("do something");
            return "abc";
        }
    });

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public String get() {
        String s = null;
        try {
            s = futureTask.get();
            System.out.println("futureTask get : " + s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        FutureTaskStudy futureTaskStudy = new FutureTaskStudy();
        futureTaskStudy.start();
        String s = futureTaskStudy.get();
    }

}
