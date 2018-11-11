package com.yl.study;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2018/10/25 0025
 * @time 下午 16:57
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class Timer {

    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        System.out.println("start:" + LocalDateTime.now().format(formatter));

        // 只执行一次
        //hashedWheelTimer.newTimeout(timeout -> System.out.println("task :" + LocalDateTime.now().format(formatter)), 1, TimeUnit.SECONDS);


        // 循环执行
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task :" + LocalDateTime.now().format(formatter));
                // 执行完成后再次注册自己
                hashedWheelTimer.newTimeout(this, 1, TimeUnit.SECONDS);
                System.out.println(this.getClass().getName());
            }
        };

        // 循环执行 （执行完后再次注册自己。。。）
        hashedWheelTimer.newTimeout(timerTask, 1, TimeUnit.SECONDS);


        /**
         *   --------------------  netty Future -------------------------------
         */


        DefaultEventExecutorGroup executors = new DefaultEventExecutorGroup(4);
        executors.submit(() -> {
            System.out.println("do something.........");
            return 100;
        }).addListener((FutureListener<Integer>) future -> {
            Integer integer = future.get();
            System.out.println("get something .....  " + integer);
        });

        /**
         *    --------------- netty promise ----------------------------
         */
        NioEventLoopGroup event = new NioEventLoopGroup(4);
        DefaultPromise<String> promise = new DefaultPromise<>(event.next());
        promise.addListener((future -> {
            if (future.isSuccess()) {
                System.out.println("promise success ...........   " + future.get());
            } else {
                System.out.println("promise failed .............");
            }
        }));

        promise.trySuccess("啊呀我要去回调了");

        for (int i = 0; i < 8; i++) {
            EventLoop next = event.next();
            System.out.println(next.toString());
        }


        event.shutdownGracefully();

    }

    public void aa(String[] args, Integer a) {

    }

}
