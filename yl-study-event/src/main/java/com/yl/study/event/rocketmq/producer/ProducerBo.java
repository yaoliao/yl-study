package com.yl.study.event.rocketmq.producer;

/**
 * 延时时间对象
 *
 * Created by 姚俊 on 2016/7/19.
 */
public class ProducerBo {

    private int timeLength;

    private String timeUnit;//时间单位 s 秒  m 分钟  h小时

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
