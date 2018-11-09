package com.yl.tool.concurrent.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2018/8/31 0031
 * @time 上午 10:45
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class QueuableCachedThreadPool extends java.util.concurrent.ThreadPoolExecutor {

    private AtomicInteger submittedCount = new AtomicInteger(0);

    public QueuableCachedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    TaskQueue workQueue, ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        workQueue.setParent(this);
        prestartAllCoreThreads();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        submittedCount.decrementAndGet();
    }

    @Override
    public void execute(Runnable command) {
        execute(command, 0L, TimeUnit.MILLISECONDS);
    }

    public void execute(Runnable command, long timeout, TimeUnit unit) {
        try {
            submittedCount.incrementAndGet();
            super.execute(command);
        } catch (Exception e) {
            try {
                TaskQueue queue = (TaskQueue) getQueue();
                if (!queue.force(command, timeout, unit)) {
                    submittedCount.decrementAndGet();
                    throw new RejectedExecutionException();
                }
            } catch (Exception ignore) {
                submittedCount.decrementAndGet();
                throw new RejectedExecutionException(ignore);
            }
        }
    }


    public Integer getSubCount() {
        return submittedCount.get();
    }

}
