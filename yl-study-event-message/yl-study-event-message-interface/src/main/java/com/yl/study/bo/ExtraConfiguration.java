package com.yl.study.bo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by DELL on 2017/11/2.
 */
public class ExtraConfiguration implements Serializable {

    private static final long serialVersionUID = -667259338587539631L;

    private Map<String, Object> params;//其他需要的业务数据

    private Long sendTime; //事件 延时发送的时间

    private EventDefineBo eventDefineBo;//事件内容

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
