package com.yl.study.event.rocketmq.producer;

import com.yl.study.event.rocketmq.config.EventConstant;
import com.yl.study.event.rocketmq.processor.MessageContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2017/11/3
 */
@Component
public class EventBasicMessageProducer {

    @Resource
    private Producer producer;

    public void send(String bizDefineCode, String nodeDefineCode, Integer bizPrimaryId) {
        this.send(bizDefineCode, nodeDefineCode, bizPrimaryId, null);
    }

    public void send(String bizDefineCode, String nodeDefineCode, Integer bizPrimaryId, String extendBody) {

        MessageContext messageContext = new MessageContext(bizDefineCode, nodeDefineCode, bizPrimaryId, extendBody, null);
        producer.send(EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_GROUPNAME,
                EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_TOPIC,
                EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_SUBSCRIBE, messageContext);
    }


}
