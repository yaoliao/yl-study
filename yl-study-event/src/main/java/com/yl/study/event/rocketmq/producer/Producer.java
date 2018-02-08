package com.yl.study.event.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.yl.study.bo.EventDefineBo;
import com.yl.study.bo.EventExecuteLogBo;
import com.yl.study.domain.EventExecuteLogDomain;
import com.yl.study.event.exception.MQException;
import com.yl.study.event.rocketmq.config.EventConstant;
import com.yl.study.event.rocketmq.config.EventManagementConfig;
import com.yl.study.event.rocketmq.processor.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息发送者
 *
 * @author DELL
 * @date 2017/10/30
 */
@Component
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private DefaultMQProducer producer;

    /*@Resource
    private EventDelayRuleDomain eventDelayRuleDomain;*/

    @Resource
    private EventExecuteLogDomain eventExecuteLogDomain;

    @PostConstruct
    public void init() {
        try {
            this.producer = new DefaultMQProducer();
            producer.setNamesrvAddr(EventManagementConfig.ROCKETMQ_NAMESVR_ADDRESS);

            //不知道为什么这里一定要设个ProducerGroup的默认值，要不然会报错。。。
            producer.setProducerGroup("BaseGroup");
            producer.setSendMsgTimeout(10000);
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        if (producer != null) {
            producer.shutdown();
        }
    }


    public void send(String groupName, String topic, String subscribe, MessageContext messageContext) {
        this.send(groupName, topic, subscribe, messageContext, "");//空字符串是为了和传时间的方法区别开
    }

    public void send(String groupName, String topic, String subscribe, MessageContext messageContext, String sourceMsgId) {
        try {
            this.send(groupName, topic, subscribe, messageContext, ProducerUtils.DEFAULT_DELAY_TIME_LEVEL, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String groupName, String topic, String subscribe, MessageContext messageContext, int delayTimeLevel, EventDefineBo eventDefined) throws MQException {
        send(groupName, topic, subscribe, messageContext, delayTimeLevel, eventDefined, null);
    }

    public void send(String groupName, String topic, String subscribe, MessageContext messageContext, int delayTimeLevel, EventDefineBo eventDefined, String sourceMsgId) throws MQException {

        try {
            if (messageContext != null) {
                this.producer.setProducerGroup(groupName);
                Message message = new Message(topic, subscribe, UUID.randomUUID().toString(), JSON.toJSONBytes(messageContext));
                if (delayTimeLevel > ProducerUtils.DEFAULT_DELAY_TIME_LEVEL) {
                    message.setDelayTimeLevel(delayTimeLevel);
                    eventDefined.setDelayLevel(delayTimeLevel);
                }

                if (eventDefined != null) {//事件分发
                    EventExecuteLogBo log = new EventExecuteLogBo(eventDefined, messageContext.getBizPrimaryId(), message.getKeys());
                    log.setSourceMsgId(sourceMsgId);
                    log.setExtend(messageContext.getExtend());
                    log.setExtraConfiguration(messageContext.getExtraConfiguration());
                    eventExecuteLogDomain.create(log);
                } else {//事件入口
                    EventDefineBo eventDefine = new EventDefineBo();
                    eventDefine.setTopic(topic);
                    eventDefine.setSubscribe(subscribe);
                    eventDefine.setConsumerGroupName(EventConstant.EventBasiceMessageConf.EVENT_CONSUMER_GROUPNAME);
                    eventDefine.setProducerGroupName(EventConstant.EventBasiceMessageConf.EVENT_PRODUCER_GROUPNAME);
                    eventDefine.setBizDefineCode(messageContext.getBizDefindCode());
                    eventDefine.setNodeDefineCode(messageContext.getNodeDefindCode());
                    eventDefine.setName("事件入口");
                    eventDefine.setID(0);
                    eventDefine.setCode("eventBasicMessage");
                    EventExecuteLogBo log = new EventExecuteLogBo(eventDefine, messageContext.getBizPrimaryId(), message.getKeys());
                    log.setSourceMsgId(sourceMsgId);
                    log.setExtend(messageContext.getExtend());
                    log.setExtraConfiguration(messageContext.getExtraConfiguration());
                    eventExecuteLogDomain.create(log);
                }
                SendResult sendResult = producer.send(message);
                if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                    //发送成功,如果是严格顺序的消息，其他状态都必须有重发机制
                    LOGGER.info("message:" + message.getKeys() + " send success.");
                } else if (SendStatus.FLUSH_DISK_TIMEOUT.equals(sendResult.getSendStatus())) {
                    //消息发送成功，但是服务器刷盘超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失
                    LOGGER.warn("message:" + message.getKeys() + SendStatus.FLUSH_DISK_TIMEOUT);
                } else if (SendStatus.FLUSH_SLAVE_TIMEOUT.equals(sendResult.getSendStatus())) {
                    //消息发送成功，但是服务器同步到 Slave 时超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失
                    LOGGER.warn("message:" + message.getKeys() + SendStatus.FLUSH_SLAVE_TIMEOUT);
                } else if (SendStatus.SLAVE_NOT_AVAILABLE.equals(sendResult.getSendStatus())) {
                    //消息发送成功，但是此时 slave 不可用，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失。
                    LOGGER.warn("message:" + message.getKeys() + SendStatus.SLAVE_NOT_AVAILABLE);
                }


            }
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    //================================   这是测试   ===========================================================

    public void sendTest(String groupName, String topic, String tag, Integer bizPrimaryId, String extend) {

        MessageContext messageContext = new MessageContext("事件模块", "事件节点", bizPrimaryId, extend, null);

        byte[] bytes = JSON.toJSONBytes(messageContext);

        Message message = new Message(topic, tag, UUID.randomUUID().toString(), bytes);
        producer.setProducerGroup(groupName);
        try {
            SendResult sendResult = producer.send(message);

            System.out.println("开始发送了========================");
            System.out.println("id: " + sendResult.getMsgId() + "result: " + sendResult.getSendStatus());

        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //发送普通消息
        DefaultMQProducer producer = new DefaultMQProducer("study-student");
        producer.setNamesrvAddr("192.168.110.233:9876");
        try {
            producer.start();

            Map<String, String> map = new HashMap<>();
            map.put("name", "yl");
            map.put("age", "13");
            String string = JSON.toJSONString(map);

            //事件测试
            Message eventMsg = new Message("study-student-topic", "study-student-tag", UUID.randomUUID().toString(),
                    JSON.toJSONBytes(new MessageContext("bizDefinedCode", "nodeDefinedCode", 2333, string, null)));

            producer.setProducerGroup("study-student-group-name");

            SendResult eventResult = producer.send(eventMsg);
            System.out.println("id: " + eventResult.getMsgId() + "result: " + eventResult.getSendStatus());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }

}
