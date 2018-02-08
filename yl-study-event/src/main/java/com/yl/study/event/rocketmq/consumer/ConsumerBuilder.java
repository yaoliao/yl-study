package com.yl.study.event.rocketmq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.yl.study.bo.EventDefineBo;
import com.yl.study.domain.EventDefineDomain;
import com.yl.study.domain.EventExecuteLogDomain;
import com.yl.study.event.exception.ConsumerConfigNotEnoughException;
import com.yl.study.event.exception.EventException;
import com.yl.study.event.rocketmq.annotation.Consumer;
import com.yl.study.event.rocketmq.config.EventManagementConfig;
import com.yl.study.event.rocketmq.processor.MessageProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2017/10/30
 */
@Component
public class ConsumerBuilder {

    @Resource
    private EventDefineDomain eventDefineDomain;

    @Resource
    private EventExecuteLogDomain eventExecuteLogDomain;

    public void build(MessageProcessor messageProcessor) throws EventException {

        Consumer consumer = messageProcessor.getClass().getAnnotation(Consumer.class);

        if (hasAllNecessaryOptions(consumer)) {
            /**
             * 如果注解中明确了消息队列的配置,直接初始化Consumer
             */
            build(consumer, messageProcessor);
        } else if (!consumer.buildAdapter().isInterface()) {
            /**
             * 如果注解中没有明确消息队列配置,去适配器上试试运气
             */
            buildByAdapter(consumer, messageProcessor);
        } else {

            String eventDefineCode = consumer.value();
            if (StringUtils.isNotEmpty(eventDefineCode)) {
                /**
                 * 如果注解中没有明确消息队列配置,则根据业务定义Code获取
                 */
                buildByDb(eventDefineCode, messageProcessor);
            } else {
                throw new ConsumerConfigNotEnoughException("init the consumer : " + consumer.value() + " error, the configuration of this consumer is not enough");
            }

        }

    }

    /**
     * 直接创建
     *
     * @param consumer
     * @param messageProcesser
     */
    private void build(Consumer consumer, MessageProcessor messageProcesser) {
        String eventDefineCode = consumer.value();
        String groupName = consumer.groupName();
        String topic = consumer.topic();
        String subscribe = consumer.subscribe();
        this.consumerInitialization(messageProcesser, eventDefineCode, groupName, topic, subscribe);
    }

    /**
     * 根据适配器创建
     *
     * @param consumer
     * @param messageProcesser
     */
    private void buildByAdapter(Consumer consumer, MessageProcessor messageProcesser) {
        try {
            Class<? extends ConsumerBuildAdapter> consumerBuildAdapterClass = consumer.buildAdapter();
            ConsumerBuildAdapter consumerBuildAdapter = consumerBuildAdapterClass.newInstance();
            String eventDefineCode = consumer.value();
            String groupName = consumerBuildAdapter.getGroupName();
            String topic = consumerBuildAdapter.getTopic();
            String subscribe = consumerBuildAdapter.getSubscribe();
            this.consumerInitialization(messageProcesser, eventDefineCode, groupName, topic, subscribe);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过DB中获取参数进行创建
     *
     * @param eventDefindCode
     * @param messageProcesser
     */
    private void buildByDb(String eventDefindCode, MessageProcessor messageProcesser) throws ConsumerConfigNotEnoughException {
        EventDefineBo eventDefind = eventDefineDomain.getByCode(eventDefindCode);
        if (eventDefind != null) {
            String groupName = eventDefind.getConsumerGroupName();
            String topic = eventDefind.getTopic();
            String subscribe = eventDefind.getSubscribe();
            this.consumerInitialization(messageProcesser, eventDefindCode, groupName, topic, subscribe);
        } else {
            throw new ConsumerConfigNotEnoughException("init the consumer : " + eventDefindCode + " error, the configuration of this consumer is not enough");
        }
    }


    /**
     * 初始化MQ的Consumer,并启动
     *
     * @param messageProcesser
     * @param eventDefineCode
     * @param groupName
     * @param topic
     * @param subscribe
     */
    public void consumerInitialization(MessageProcessor messageProcesser, String eventDefineCode, String groupName, String topic, String subscribe) {

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
            consumer.setNamesrvAddr(EventManagementConfig.ROCKETMQ_NAMESVR_ADDRESS);
            consumer.subscribe(topic, subscribe);
            //修改默认最小线程数
            consumer.setConsumeThreadMin(5);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new DefaultMessageListener(messageProcesser, eventExecuteLogDomain));
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }


    /**
     * 判断注解上是否有MQ相关的完整配置
     * 三个关键参数均不可为空
     *
     * @param consumer
     * @return
     */
    private boolean hasAllNecessaryOptions(Consumer consumer) {
        return StringUtils.isNotBlank(consumer.topic())
                && StringUtils.isNotBlank(consumer.subscribe())
                && StringUtils.isNotBlank(consumer.groupName());
    }

}
