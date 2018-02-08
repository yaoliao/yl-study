package com.yl.study.event.rocketmq.config;

import com.yl.study.event.rocketmq.bo.EventDefineBo;

import java.io.Serializable;
import java.util.Map;

/**
 * 事件传参 额外配置类
 *
 * @author DELL
 * @date 2017/10/30
 */
public class ExtraConfiguration implements Serializable {

    private static final long serialVersionUID = -4281900671060473772L;

    /**
     * 其他需要的业务数据
     */
    private Map<String, Object> params;

    /**
     * 事件 延时发送的时间
     */
    private Long sendTime;

    /**
     * 事件内容
     */
    private EventDefineBo eventDefineBo;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public EventDefineBo getEventDefineBo() {
        return eventDefineBo;
    }

    public void setEventDefineBo(EventDefineBo eventDefineBo) {
        this.eventDefineBo = eventDefineBo;
    }
}
