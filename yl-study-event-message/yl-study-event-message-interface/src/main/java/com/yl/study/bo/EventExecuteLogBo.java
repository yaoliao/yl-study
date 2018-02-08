package com.yl.study.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 2017/11/2.
 */
public class EventExecuteLogBo implements Serializable {

    private static final long serialVersionUID = -6181754442561157978L;

    /**
     * 日志主键
     */
    private Long id;

    /**
     * 业务定义ID
     */
    private Integer bizId;

    /**
     * 业务定义Code
     */
    private String bizCode;

    /**
     * 业务定义名称
     */
    private String bizName;

    /**
     * node定义ID
     */
    private Integer nodeId;

    /**
     * node定义Code
     */
    private String nodeCode;

    /**
     * node定义名称
     */
    private String nodeName;

    /**
     * Event定义ID
     */
    private Integer eventDefineId;

    /**
     * event定义Code
     */
    private String eventDefineCode;

    /**
     * event名称
     */
    private String evnetDefineName;

    /**
     * 步骤,用于严格顺序消费
     */
    private Integer step;

    /**
     * 业务主ID
     */
    private Integer bizPrimaryId;

    /**
     * mq指定topic
     */
    private String topic;

    /**
     * mq指定subscribe
     */
    private String subscribe;

    /**
     * 生产者组名称
     */
    private String producerGroupName;

    /**
     * 消费者组名称
     */
    private String consumerGroupName;

    /**
     * mq消息Id,唯一标识
     */
    private String msgId;

    /**
     * 延时级别
     */
    private Integer delayLevel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发送到队列时间
     */
    private Date sendTime;

    /**
     * 消费开始时间
     */
    private Date consumeStartTime;


    /**
     * 消费结束时间
     */
    private Date consumeEndTime;

    /**
     * 消费状态
     * 待消费:0
     * 消费中:1
     * 消费成功:2
     * 消费失败:3
     */
    private Integer consumeStatus;

    /**
     * 消费异常
     */
    private String consumeException;

    /**
     * 重试次数
     */
    private Integer retry;

    /**
     * 乐观锁
     * 最后更新时间
     */
    private Date lastModifyTime;

    private String extend;

    private ExtraConfiguration extraConfiguration;

    private String sourceMsgId;

    public EventExecuteLogBo() {
    }

    public EventExecuteLogBo(EventDefineBo eventDefine, Integer bizPrimaryId, String msgId) {

        this.msgId = msgId;
        this.bizCode = eventDefine.getBizDefineCode();
        this.bizId = eventDefine.getBizDefineId();
        this.bizName = eventDefine.getBizDefineName();
        this.nodeCode = eventDefine.getNodeDefineCode();
        this.nodeId = eventDefine.getNodeDefineId();
        this.nodeName = eventDefine.getNodeDefineName();
        this.eventDefineCode = eventDefine.getCode();
        this.eventDefineId = eventDefine.getID();
        this.evnetDefineName = eventDefine.getName();
        this.step = eventDefine.getStep();
        this.bizPrimaryId = bizPrimaryId;
        this.topic = eventDefine.getTopic();
        this.subscribe = eventDefine.getSubscribe();
        this.producerGroupName = eventDefine.getProducerGroupName();
        this.consumerGroupName = eventDefine.getConsumerGroupName();
        this.delayLevel = eventDefine.getDelayLevel();
        this.createTime = new Date();
        this.sendTime = new Date();
        this.lastModifyTime = new Date();
        this.consumeStatus = 0;
        this.retry = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getEventDefineId() {
        return eventDefineId;
    }

    public void setEventDefineId(Integer eventDefineId) {
        this.eventDefineId = eventDefineId;
    }

    public String getEventDefineCode() {
        return eventDefineCode;
    }

    public void setEventDefineCode(String eventDefineCode) {
        this.eventDefineCode = eventDefineCode;
    }

    public String getEvnetDefineName() {
        return evnetDefineName;
    }

    public void setEvnetDefineName(String evnetDefineName) {
        this.evnetDefineName = evnetDefineName;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getBizPrimaryId() {
        return bizPrimaryId;
    }

    public void setBizPrimaryId(Integer bizPrimaryId) {
        this.bizPrimaryId = bizPrimaryId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getDelayLevel() {
        return delayLevel;
    }

    public void setDelayLevel(Integer delayLevel) {
        this.delayLevel = delayLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getConsumeStartTime() {
        return consumeStartTime;
    }

    public void setConsumeStartTime(Date consumeStartTime) {
        this.consumeStartTime = consumeStartTime;
    }

    public Date getConsumeEndTime() {
        return consumeEndTime;
    }

    public void setConsumeEndTime(Date consumeEndTime) {
        this.consumeEndTime = consumeEndTime;
    }

    public Integer getConsumeStatus() {
        return consumeStatus;
    }

    public void setConsumeStatus(Integer consumeStatus) {
        this.consumeStatus = consumeStatus;
    }

    public String getConsumeException() {
        return consumeException;
    }

    public void setConsumeException(String consumeException) {
        this.consumeException = consumeException;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public ExtraConfiguration getExtraConfiguration() {
        return extraConfiguration;
    }

    public void setExtraConfiguration(ExtraConfiguration extraConfiguration) {
        this.extraConfiguration = extraConfiguration;
    }

    public String getSourceMsgId() {
        return sourceMsgId;
    }

    public void setSourceMsgId(String sourceMsgId) {
        this.sourceMsgId = sourceMsgId;
    }
}
