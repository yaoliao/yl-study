package com.yl.study.example10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 * @date 2018/2/12
 */
public class CallableStudy {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(System.currentTimeMillis() + "   睡觉结束了1111111111。。。" + finalI);
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(System.currentTimeMillis() + "   睡觉结束了222222222。。。" + finalI);
                    return "callable return" + finalI;
                }
            });
            list.add(future);
        }
        for (Future<String> f : list) {
            try {
                System.out.println(System.currentTimeMillis() + "阻塞前");
                System.out.println(System.currentTimeMillis() + "   " + f.get());
                System.out.println(System.currentTimeMillis() + "阻塞后");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }


}
