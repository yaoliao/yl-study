package com.yl.study.event;

import com.yl.study.event.exception.DelayRuleException;
import com.yl.study.event.exception.EventException;
import com.yl.study.event.rocketmq.annotation.Consumer;
import com.yl.study.event.rocketmq.processor.MessageContext;
import com.yl.study.event.rocketmq.processor.MessageProcessor;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @date 2017/10/30
 */
@Consumer(value = "student-event", groupName = "study-student-group-name",
        topic = "study-student-topic", subscribe = "study-student-tag")
@Service
public class StudentConsumer implements MessageProcessor {

    @Override
    public void process(MessageContext messageContext) throws EventException, DelayRuleException {

        Integer bizPrimaryId = messageContext.getBizPrimaryId();
        String extend = messageContext.getExtend();

        //根据业务ID(bizPrimaryId)进行相关操作
        // .......

        System.out.println("bizPrimaryId ========== " + bizPrimaryId);
        System.out.println("extend ========== " + extend);
        System.out.println(messageContext.getNodeDefindCode());

    }
}
