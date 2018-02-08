package com.yl.study.event.rocketmq.producer;

/**
 * @author DELL
 * @date 2017/11/2
 */
public class ProducerUtils {

    /**
     * 默认延时时长(单位:毫秒)
     */
    public final static long DEFAULT_DELAY_TIME = 0;
    /**
     * 默认延时级别
     */
    public final static int DEFAULT_DELAY_MILLISECOND_FOR_HOUR = 3600000;
    /**
     * 默认延时级别
     */
    public final static int DEFAULT_DELAY_TIME_LEVEL = 0;

    /**
     * 1秒毫秒数
     */
    private final static double MILLISECOND_FOR_SECOND = 1000.0;

    /**
     * 1分钟毫秒数
     */
    private final static double MILLISECOND_FOR_MINUTE = 60000.0;

    /**
     * 1小时毫秒数
     */
    private final static double MILLISECOND_FOR_HOUR = 3600000.0;

    /**
     * 这里需要一个根据毫秒数算出延时级别的工具类
     * <p>
     * 根据delayTime毫秒数,计算小时数,向上取整,不足一小时,按一小时算
     */
    public static ProducerBo getDelayTimeLevel(long delayMillisecond) {
        if (delayMillisecond > 0) {
            ProducerBo producerBo = new ProducerBo();
            if (delayMillisecond / MILLISECOND_FOR_MINUTE >= 1) {//分钟 或 小时 级别
                if (delayMillisecond / MILLISECOND_FOR_HOUR >= 1) {//小时
                    producerBo.setTimeLength((int) Math.ceil(delayMillisecond / MILLISECOND_FOR_HOUR));
                    producerBo.setTimeUnit("h");
                } else {//分钟
                    producerBo.setTimeLength((int) Math.ceil(delayMillisecond / MILLISECOND_FOR_MINUTE));
                    producerBo.setTimeUnit("m");
                }
            } else {//秒的级别
                producerBo.setTimeLength((int) Math.ceil(delayMillisecond / MILLISECOND_FOR_SECOND));
                producerBo.setTimeUnit("s");
            }
            return producerBo;
        }
        return null;
    }

}
