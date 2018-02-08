package com.yl.study.event.rocketmq.processor;

import com.yl.study.event.exception.DelayRuleException;
import com.yl.study.event.exception.EventException;

/**
 * @author DELL
 * @date 2017/10/30
 */
public interface MessageProcessor {

    void process(MessageContext messageContext) throws EventException, DelayRuleException;

}
