package com.yl.study.example1.spring.transaction;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 * @date 2017/10/31
 */
public class TransactionTest {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String JDBC_URL = "jdbc:mysql://116.196.120.187:3306/study";

    private static final String JDBC_USERNAME = "root";

    private static final String JDBC_PASSWORD = "123456";


    public static void main(String[] args) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);

        final UserService service = new UserService(dataSource);

        final CountDownLatch latch = new CountDownLatch(10);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                service.action();
                latch.countDown();
            });

        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();

    }


}
