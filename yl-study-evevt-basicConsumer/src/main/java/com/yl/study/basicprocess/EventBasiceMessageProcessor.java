package com.yl.study.basicprocess;

import com.yl.study.event.exception.DelayRuleException;
import com.yl.study.event.exception.EventException;
import com.yl.study.event.rocketmq.annotation.Consumer;
import com.yl.study.event.rocketmq.processor.MessageContext;
import com.yl.study.event.rocketmq.processor.MessageProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 基础消息消费者
 *
 * @author DELL
 * @date 2017/11/3
 */
@Component
@Consumer(value = "eventBasic", buildAdapter = EventBasiceMessageConsumerBuilderAdapter.class)
public class EventBasiceMessageProcessor implements MessageProcessor {

    @Resource
    private EventConstructControl eventConstructControl;

    @Override
    public void process(MessageContext messageContext) throws EventException, DelayRuleException {

        String nodeDefindCode = messageContext.getNodeDefindCode();
        Integer bizPrimaryId = messageContext.getBizPrimaryId();
        String extend = messageContext.getExtend();

        eventConstructControl.constructAndSend(nodeDefindCode, bizPrimaryId, extend);

    }
}
