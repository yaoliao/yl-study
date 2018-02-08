package com.yl.study.event.rocketmq.consumer;

import com.yl.study.event.exception.EventException;
import com.yl.study.event.exception.EventRunException;
import com.yl.study.event.rocketmq.annotation.Consumer;
import com.yl.study.event.rocketmq.config.EventManagementConfig;
import com.yl.study.event.rocketmq.processor.MessageProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2017/10/30
 */
@Component
public class ConsumerLoaderProcessor implements BeanPostProcessor {

    @Resource
    private ConsumerBuilder consumerBuilder;

    /**
     * 这里写这个是为了使 EventManagementConfig 这个配置类 在ConsumerLoaderProcessor之前就加载为bean
     */
    @Resource
    private EventManagementConfig eventManagementConfig;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().isAnnotationPresent(Consumer.class)) {
            if (!(bean instanceof MessageProcessor)) {
                throw new EventRunException("添加 [Consumer] 注解的类需继承 [MessageProcessor]");
            }
            MessageProcessor messageProcessor = (MessageProcessor) bean;
            try {
                consumerBuilder.build(messageProcessor);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

}
