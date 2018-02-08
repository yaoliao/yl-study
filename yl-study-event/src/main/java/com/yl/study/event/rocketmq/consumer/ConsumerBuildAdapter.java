package com.yl.study.event.rocketmq.consumer;

/**
 * Consumer构建适配器,允许自由定义关键属性值
 *
 * @author DELL
 * @date 2017/10/30
 */
public interface ConsumerBuildAdapter {

    String getGroupName();

    String getTopic();

    String getSubscribe();

}
