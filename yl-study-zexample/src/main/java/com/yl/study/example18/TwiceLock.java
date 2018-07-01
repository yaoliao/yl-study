package com.yl.study.example18;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
public class TwiceLock {

    private final Sync sync = new Sync(2);

    static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            setState(2);
        }

        @Override
        protected int tryAcquireShared(int arg) {

            for (; ; ) {
                int count = getState();
                int newCount = count - arg;
                if (newCount < 0 || compareAndSetState(count, newCount)) { // 小于0获取同步状态失败
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {

            for (; ; ) {
                int count = getState();
                int newCount = arg + count;
                if (compareAndSetState(count, newCount)) {
                    return true;
                }
            }
        }
    }

    public void lock() {
        sync.acquireShared(1);
    }

    public void unlock() {
        sync.releaseShared(1);
    }


}
