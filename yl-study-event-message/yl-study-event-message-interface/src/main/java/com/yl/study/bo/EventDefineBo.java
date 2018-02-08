package com.yl.study.bo;

import com.yl.common.utils.BeanUtil;
import com.yl.study.enums.DelayType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 2017/11/2.
 */
public class EventDefineBo implements Serializable, BeanUtil.ConversionCustomizble {

    private static final long serialVersionUID = -2393273033850029598L;
    /**
     * Event定义ID
     */
    private Integer ID;

    /**
     * 业务定义ID
     */
    private Integer bizDefineId;

    /**
     * 业务定义Code
     */
    private String bizDefineCode;

    /**
     * 业务名称
     */
    private String bizDefineName;

    /**
     * node定义ID
     */
    private Integer nodeDefineId;

    /**
     * node定义Code
     */
    private String nodeDefineCode;

    /**
     * 节点名称
     */
    private String nodeDefineName;

    /**
     * event定义Code
     */
    private String code;

    /**
     * event名称
     */
    private String name;

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
     * 是否启用
     */
    private boolean isEnabled;

    /**
     * 步骤,用于严格顺序消费
     */
    private Integer step;

    /**
     * 延时消费类型
     */
    private DelayType delayType;

    private Integer delayTypeInt;

    /**
     * 固定延时级别
     */
    private Integer delayLevel;

    /**
     * 固定延时时长
     */
    private Long delayMillisecond;

    /**
     * 自定义延时计算器Code
     */
    private String delayCalculatorCode;

    private Date lastModifyTime;
    /**
     * 最大重发次数
     */
    private Integer retry;

    public EventDefineBo() {
    }

    public EventDefineBo(Integer bizDefineId, String bizDefineCode, String bizDefineName, Integer nodeDefineId, String nodeDefineCode, String nodeDefineName, String code, String name, String topic, String subscribe, String producerGroupName, String consumerGroupName, boolean isEnabled, Integer step, DelayType delayType, Integer delayTypeInt, Integer delayLevel, Long delayMillisecond, String delayCalculatorCode) {
        this.bizDefineId = bizDefineId;
        this.bizDefineCode = bizDefineCode;
        this.bizDefineName = bizDefineName;
        this.nodeDefineId = nodeDefineId;
        this.nodeDefineCode = nodeDefineCode;
        this.nodeDefineName = nodeDefineName;
        this.code = code;
        this.name = name;
        this.topic = topic;
        this.subscribe = subscribe;
        this.producerGroupName = producerGroupName;
        this.consumerGroupName = consumerGroupName;
        this.isEnabled = isEnabled;
        this.step = step;
        this.delayType = delayType;
        this.delayTypeInt = delayTypeInt;
        this.delayLevel = delayLevel;
        this.delayMillisecond = delayMillisecond;
        this.delayCalculatorCode = delayCalculatorCode;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBizDefineId() {
        return bizDefineId;
    }

    public void setBizDefineId(Integer bizDefineId) {
        this.bizDefineId = bizDefineId;
    }

    public String getBizDefineCode() {
        return bizDefineCode;
    }

    public void setBizDefineCode(String bizDefineCode) {
        this.bizDefineCode = bizDefineCode;
    }

    public String getBizDefineName() {
        return bizDefineName;
    }

    public void setBizDefineName(String bizDefineName) {
        this.bizDefineName = bizDefineName;
    }

    public Integer getNodeDefineId() {
        return nodeDefineId;
    }

    public void setNodeDefineId(Integer nodeDefineId) {
        this.nodeDefineId = nodeDefineId;
    }

    public String getNodeDefineCode() {
        return nodeDefineCode;
    }

    public void setNodeDefineCode(String nodeDefineCode) {
        this.nodeDefineCode = nodeDefineCode;
    }

    public String getNodeDefineName() {
        return nodeDefineName;
    }

    public void setNodeDefineName(String nodeDefineName) {
        this.nodeDefineName = nodeDefineName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public DelayType getDelayType() {
        return delayType;
    }

    public void setDelayType(DelayType delayType) {
        this.delayType = delayType;
    }

    public Integer getDelayTypeInt() {
        return delayTypeInt;
    }

    public void setDelayTypeInt(Integer delayTypeInt) {
        this.delayTypeInt = delayTypeInt;
    }

    public Integer getDelayLevel() {
        return delayLevel;
    }

    public void setDelayLevel(Integer delayLevel) {
        this.delayLevel = delayLevel;
    }

    public Long getDelayMillisecond() {
        return delayMillisecond;
    }

    public void setDelayMillisecond(Long delayMillisecond) {
        this.delayMillisecond = delayMillisecond;
    }

    public String getDelayCalculatorCode() {
        return delayCalculatorCode;
    }

    public void setDelayCalculatorCode(String delayCalculatorCode) {
        this.delayCalculatorCode = delayCalculatorCode;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    @Override
    public String toString() {
        return "EventDefineBo{" +
                "id=" + ID +
                ", bizDefineId=" + bizDefineId +
                ", bizDefineCode='" + bizDefineCode + '\'' +
                ", bizDefineName='" + bizDefineName + '\'' +
                ", nodeDefineId=" + nodeDefineId +
                ", nodeDefineCode='" + nodeDefineCode + '\'' +
                ", nodeDefineName='" + nodeDefineName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", producerGroupName='" + producerGroupName + '\'' +
                ", consumerGroupName='" + consumerGroupName + '\'' +
                ", isEnabled=" + isEnabled +
                ", step=" + step +
                ", delayType=" + delayType +
                ", delayTypeInt=" + delayTypeInt +
                ", delayLevel=" + delayLevel +
                ", delayMillisecond=" + delayMillisecond +
                ", delayCalculatorCode='" + delayCalculatorCode + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", retry=" + retry +
                '}';
    }

    @Override
    public void convertOthers(Object srcObj) {
        Object delayType = BeanUtil.getPropValue(srcObj, "delayType");
        if (delayType != null && delayType instanceof Integer) {
            this.setDelayType(DelayType.getByValue((Integer) delayType));
        }
    }
}
