package com.yl.tool.concurrent.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2018/8/31 0031
 * @time 下午 13:28
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class TaskQueue extends LinkedBlockingQueue<Runnable> {

    private transient QueuableCachedThreadPool parent;

    public TaskQueue(int capacity) {
        super(capacity);
    }

    public void setParent(QueuableCachedThreadPool parent) {
        this.parent = parent;
    }

    public boolean force(Runnable runnable) {
        if (parent.isShutdown()) {
            throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
        }
        return super.offer(runnable);
    }

    public boolean force(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
        if (parent.isShutdown()) {
            throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
        }
        return super.offer(runnable, timeout, unit);
    }

    @Override
    public boolean offer(Runnable runnable) {

        Integer currentPoolSize = parent.getPoolSize();

        if (currentPoolSize >= parent.getMaximumPoolSize()) {
            return super.offer(runnable);
        }

        if (currentPoolSize >= parent.getSubCount()) {
            return super.offer(runnable);
        }

        if (currentPoolSize < parent.getMaximumPoolSize()) {
            return false;
        }

        return super.offer(runnable);
    }
}
