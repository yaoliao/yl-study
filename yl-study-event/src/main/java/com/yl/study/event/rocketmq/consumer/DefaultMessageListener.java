package com.yl.study.event.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yl.study.bo.EventExecuteLogBo;
import com.yl.study.domain.EventExecuteLogDomain;
import com.yl.study.event.exception.DelayRuleException;
import com.yl.study.event.exception.EventException;
import com.yl.study.event.exception.MessageProcessFailException;
import com.yl.study.event.rocketmq.config.EventConsumeStatus;
import com.yl.study.event.rocketmq.processor.MessageContext;
import com.yl.study.event.rocketmq.processor.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 消息处理监听,默认
 *
 * @author DELL
 * @date 2017/10/30
 */
public class DefaultMessageListener implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageListener.class);

    private MessageProcessor messageProcessor;

    private EventExecuteLogDomain eventExecuteLogDomain;

    public DefaultMessageListener(MessageProcessor messageProcessor, EventExecuteLogDomain eventExecuteLogDomain) {
        this.messageProcessor = messageProcessor;
        this.eventExecuteLogDomain = eventExecuteLogDomain;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        for (MessageExt messageExt : list) {
            if (messageExt == null || messageExt.getBody() == null) {
                continue;
            }

            //消息唯一ID
            String msgkey = messageExt.getKeys();

            try {
                /**
                 * 根据msgId去数据库获取Event消息执行状态,如果已经执行成功,不再执行
                 */
                EventExecuteLogBo eventExecuteLogBo = eventExecuteLogDomain.getByMsgId(msgkey);

                if (eventExecuteLogBo != null) {
                    MessageContext messageContext = JSON.parseObject(messageExt.getBody(), MessageContext.class);

                    if (EventConsumeStatus.WAIT.equals(eventExecuteLogBo.getConsumeStatus())) {

                        /**
                         * 记录开始消费日志
                         */
                        eventExecuteLogDomain.consumeStart(msgkey);

                        try {
                            messageProcessor.process(messageContext);
                        } catch (EventException | DelayRuleException e) {

                            LOGGER.warn("The Message processed unsuccessfully, msgId= " + msgkey);
                            LOGGER.warn("cause by: " + e.getMessage());

                            //TODO
                            //eventExecuteLogDomain.consumeFail(msgkey, e.getMessage());
                            throw new MessageProcessFailException(e.getMessage(), e);
                        }

                        /**
                         * 记录消费成功日志
                         */
                        eventExecuteLogDomain.consumeSuccess(msgkey);

                    } else if (EventConsumeStatus.ONGOING.equals(eventExecuteLogBo.getConsumeStatus())) {
                        LOGGER.warn("The Message status 1 Consumed, msgId= " + msgkey);
                    } else if (EventConsumeStatus.FAIL.equals(eventExecuteLogBo.getConsumeStatus())) {

                        LOGGER.warn("The Message Consumed, msgId= " + msgkey);
                        try {
                            messageProcessor.process(messageContext);
                        } catch (EventException | DelayRuleException e) {
                            e.printStackTrace();
                            LOGGER.warn("The Message processed unsuccessfully, msgId= " + msgkey);
                            LOGGER.warn("cause by: " + e.getMessage());

                            //TODO
                            //eventExecuteLogDomain.consumeFail(msgkey, e.getMessage());
                            throw new MessageProcessFailException(e.getMessage(), e);
                        }

                    }

                } else {
                    LOGGER.warn("The Message's executeLog undefined, msgId= " + msgkey);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            } catch (Exception e) {
                /**
                 * 记录失败数据以及异常信息
                 */

                //TODO
                //eventExecuteLogDomain.consumeFail(msgkey, e.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
