package com.yl.study.basicprocess;

import com.yl.study.bo.EventDefineBo;
import com.yl.study.domain.EventDefineDomain;
import com.yl.study.enums.DelayType;
import com.yl.study.event.exception.MQException;
import com.yl.study.event.rocketmq.processor.MessageContext;
import com.yl.study.event.rocketmq.producer.Producer;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DELL
 * @date 2017/11/3
 */
@Component
public class EventConstructControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConstructControl.class);

    @Resource
    private Producer producer;

    @Resource
    private EventDefineDomain eventDefineDomain;


    /**
     * 根据节点定义nodeDefineCode构建事件
     *
     * @param nodeDefineCode
     * @param bizPrimaryId
     * @param extend
     */
    public void constructAndSend(String nodeDefineCode, Integer bizPrimaryId, String extend) throws MQException {

        List<EventDefineBo> eventDefineBos = eventDefineDomain.findByNodeDefineCode(nodeDefineCode);

        if (CollectionUtils.isNotEmpty(eventDefineBos)) {
            for (EventDefineBo eventDefined : eventDefineBos) {
                this.toSend(eventDefined, bizPrimaryId, extend);
            }
        }

    }

    private void toSend(EventDefineBo eventDefined, Integer bizPrimaryId, String extend) throws MQException {

        if (eventDefined == null || bizPrimaryId == null) {
            LOGGER.warn("eventDefined or bizPrimaryId mast not be null.");
            return;
        }

        String groupName = eventDefined.getProducerGroupName();
        String topic = eventDefined.getTopic();
        String subscribe = eventDefined.getSubscribe();

        /**
         * 创建一个事件消息体
         */
        MessageContext messageContext = new MessageContext();
        messageContext.setBizDefindCode(eventDefined.getBizDefineCode());
        messageContext.setNodeDefindCode(eventDefined.getNodeDefineCode());
        messageContext.setBizPrimaryId(bizPrimaryId);
        messageContext.setExtend(extend);

        if (eventDefined.getDelayType() == null || DelayType.NONE.equals(eventDefined.getDelayType())) {

            //默认延时 1个级别，以防数据库未刷新，就消费事件
            producer.send(groupName, topic, subscribe, messageContext, 1, eventDefined);

        } else {
            if (DelayType.FIXED_DELAY_LEVEL.equals(eventDefined.getDelayType())) {
                /**
                 * 固定延时level
                 */
                if (eventDefined.getDelayLevel() != null) {
                    producer.send(groupName, topic, subscribe, messageContext, eventDefined.getDelayLevel(), eventDefined);
                } else {
                    LOGGER.warn("eventDefined delayType equals fixed_delay_level, delayLevel mast not be null!");
                }
            }
        }

    }


}
