package com.yl.study.example4;

import com.google.common.util.concurrent.Striped;

import java.util.concurrent.locks.Lock;

/**
 * @see com.yl.study.cache.CacheTemplateServiceV2
 *
 * @author DELL
 * @date 2017/11/10
 */
public class StripedTest {

    /**
     * 创建一个弱引用的Striped<Lock>
     */
    private static final Striped<Lock> LOCK_STRIPED = Striped.lazyWeakLock(1024);


    public void method(Integer id) {
        Lock lock = LOCK_STRIPED.get(id);
        try {
            lock.lock();

            //process business
            //.......

        } finally {
            lock.unlock();
        }

    }

}
