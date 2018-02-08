package com.yl.study.basicprocess;

import com.yl.study.event.rocketmq.config.EventConstant;
import com.yl.study.event.rocketmq.consumer.ConsumerBuildAdapter;

/**
 *
 * @author DELL
 * @date 2017/11/3
 */
public class EventBasiceMessageConsumerBuilderAdapter implements ConsumerBuildAdapter {
    @Override
    public String getGroupName() {
        return EventConstant.EventBasiceMessageConf.EVENT_CONSUMER_GROUPNAME;
    }

    @Override
    public String getTopic() {
        return EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_TOPIC;
    }

    @Override
    public String getSubscribe() {
        return EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_SUBSCRIBE;
    }
}
