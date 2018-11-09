package com.yl.tool.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Administrator
 * @date 2018/8/31 0031
 * @time 下午 14:39
 * @function 功能： 时间间隔限制器
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class TimeIntervalLimiter {

    private AtomicLong time = new AtomicLong(0);

    /**
     * 间隔时间
     */
    private Long intervalTime;

    public TimeIntervalLimiter(long time, TimeUnit unit) {
        this.intervalTime = unit.toMillis(time);
    }

    public boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();
        long beforeTime = time.get();
        return currentTimeMillis - beforeTime >= intervalTime && time.compareAndSet(beforeTime, currentTimeMillis);
    }
}
