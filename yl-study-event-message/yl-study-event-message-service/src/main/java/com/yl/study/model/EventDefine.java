package com.yl.study.model;

import java.io.Serializable;
import java.util.Date;

public class EventDefine implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FID
     *
     * @mbg.generated
     */
    private Integer ID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FName
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FCode
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FBizDefineId
     *
     * @mbg.generated
     */
    private Integer bizDefineId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FBizDefineName
     *
     * @mbg.generated
     */
    private String bizDefineName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FBizDefineCode
     *
     * @mbg.generated
     */
    private String bizDefineCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FNodeDefineId
     *
     * @mbg.generated
     */
    private Integer nodeDefineId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FNodeDefineName
     *
     * @mbg.generated
     */
    private String nodeDefineName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FNodeDefineCode
     *
     * @mbg.generated
     */
    private String nodeDefineCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FTopic
     *
     * @mbg.generated
     */
    private String topic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FSubscribe
     *
     * @mbg.generated
     */
    private String subscribe;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FProducerGroupName
     *
     * @mbg.generated
     */
    private String producerGroupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FConsumerGroupName
     *
     * @mbg.generated
     */
    private String consumerGroupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FIsEnabled
     *
     * @mbg.generated
     */
    private Integer isEnabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FStep
     *
     * @mbg.generated
     */
    private Integer step;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FDelayType
     *
     * @mbg.generated
     */
    private Integer delayType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FDelayLevel
     *
     * @mbg.generated
     */
    private Integer delayLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FDelayCalculatorCode
     *
     * @mbg.generated
     */
    private String delayCalculatorCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FLastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_Evt_EventDefine.FRetry
     *
     * @mbg.generated
     */
    private Integer retry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FID
     *
     * @return the value of t_Evt_EventDefine.FID
     *
     * @mbg.generated
     */
    public Integer getID() {
        return ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FID
     *
     * @param ID the value for t_Evt_EventDefine.FID
     *
     * @mbg.generated
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FName
     *
     * @return the value of t_Evt_EventDefine.FName
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FName
     *
     * @param name the value for t_Evt_EventDefine.FName
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FCode
     *
     * @return the value of t_Evt_EventDefine.FCode
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FCode
     *
     * @param code the value for t_Evt_EventDefine.FCode
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FBizDefineId
     *
     * @return the value of t_Evt_EventDefine.FBizDefineId
     *
     * @mbg.generated
     */
    public Integer getBizDefineId() {
        return bizDefineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FBizDefineId
     *
     * @param bizDefineId the value for t_Evt_EventDefine.FBizDefineId
     *
     * @mbg.generated
     */
    public void setBizDefineId(Integer bizDefineId) {
        this.bizDefineId = bizDefineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FBizDefineName
     *
     * @return the value of t_Evt_EventDefine.FBizDefineName
     *
     * @mbg.generated
     */
    public String getBizDefineName() {
        return bizDefineName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FBizDefineName
     *
     * @param bizDefineName the value for t_Evt_EventDefine.FBizDefineName
     *
     * @mbg.generated
     */
    public void setBizDefineName(String bizDefineName) {
        this.bizDefineName = bizDefineName == null ? null : bizDefineName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FBizDefineCode
     *
     * @return the value of t_Evt_EventDefine.FBizDefineCode
     *
     * @mbg.generated
     */
    public String getBizDefineCode() {
        return bizDefineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FBizDefineCode
     *
     * @param bizDefineCode the value for t_Evt_EventDefine.FBizDefineCode
     *
     * @mbg.generated
     */
    public void setBizDefineCode(String bizDefineCode) {
        this.bizDefineCode = bizDefineCode == null ? null : bizDefineCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FNodeDefineId
     *
     * @return the value of t_Evt_EventDefine.FNodeDefineId
     *
     * @mbg.generated
     */
    public Integer getNodeDefineId() {
        return nodeDefineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FNodeDefineId
     *
     * @param nodeDefineId the value for t_Evt_EventDefine.FNodeDefineId
     *
     * @mbg.generated
     */
    public void setNodeDefineId(Integer nodeDefineId) {
        this.nodeDefineId = nodeDefineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FNodeDefineName
     *
     * @return the value of t_Evt_EventDefine.FNodeDefineName
     *
     * @mbg.generated
     */
    public String getNodeDefineName() {
        return nodeDefineName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FNodeDefineName
     *
     * @param nodeDefineName the value for t_Evt_EventDefine.FNodeDefineName
     *
     * @mbg.generated
     */
    public void setNodeDefineName(String nodeDefineName) {
        this.nodeDefineName = nodeDefineName == null ? null : nodeDefineName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FNodeDefineCode
     *
     * @return the value of t_Evt_EventDefine.FNodeDefineCode
     *
     * @mbg.generated
     */
    public String getNodeDefineCode() {
        return nodeDefineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FNodeDefineCode
     *
     * @param nodeDefineCode the value for t_Evt_EventDefine.FNodeDefineCode
     *
     * @mbg.generated
     */
    public void setNodeDefineCode(String nodeDefineCode) {
        this.nodeDefineCode = nodeDefineCode == null ? null : nodeDefineCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FTopic
     *
     * @return the value of t_Evt_EventDefine.FTopic
     *
     * @mbg.generated
     */
    public String getTopic() {
        return topic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FTopic
     *
     * @param topic the value for t_Evt_EventDefine.FTopic
     *
     * @mbg.generated
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FSubscribe
     *
     * @return the value of t_Evt_EventDefine.FSubscribe
     *
     * @mbg.generated
     */
    public String getSubscribe() {
        return subscribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FSubscribe
     *
     * @param subscribe the value for t_Evt_EventDefine.FSubscribe
     *
     * @mbg.generated
     */
    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe == null ? null : subscribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FProducerGroupName
     *
     * @return the value of t_Evt_EventDefine.FProducerGroupName
     *
     * @mbg.generated
     */
    public String getProducerGroupName() {
        return producerGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FProducerGroupName
     *
     * @param producerGroupName the value for t_Evt_EventDefine.FProducerGroupName
     *
     * @mbg.generated
     */
    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName == null ? null : producerGroupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FConsumerGroupName
     *
     * @return the value of t_Evt_EventDefine.FConsumerGroupName
     *
     * @mbg.generated
     */
    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FConsumerGroupName
     *
     * @param consumerGroupName the value for t_Evt_EventDefine.FConsumerGroupName
     *
     * @mbg.generated
     */
    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName == null ? null : consumerGroupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FIsEnabled
     *
     * @return the value of t_Evt_EventDefine.FIsEnabled
     *
     * @mbg.generated
     */
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FIsEnabled
     *
     * @param isEnabled the value for t_Evt_EventDefine.FIsEnabled
     *
     * @mbg.generated
     */
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FStep
     *
     * @return the value of t_Evt_EventDefine.FStep
     *
     * @mbg.generated
     */
    public Integer getStep() {
        return step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FStep
     *
     * @param step the value for t_Evt_EventDefine.FStep
     *
     * @mbg.generated
     */
    public void setStep(Integer step) {
        this.step = step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FDelayType
     *
     * @return the value of t_Evt_EventDefine.FDelayType
     *
     * @mbg.generated
     */
    public Integer getDelayType() {
        return delayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FDelayType
     *
     * @param delayType the value for t_Evt_EventDefine.FDelayType
     *
     * @mbg.generated
     */
    public void setDelayType(Integer delayType) {
        this.delayType = delayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FDelayLevel
     *
     * @return the value of t_Evt_EventDefine.FDelayLevel
     *
     * @mbg.generated
     */
    public Integer getDelayLevel() {
        return delayLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FDelayLevel
     *
     * @param delayLevel the value for t_Evt_EventDefine.FDelayLevel
     *
     * @mbg.generated
     */
    public void setDelayLevel(Integer delayLevel) {
        this.delayLevel = delayLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FDelayCalculatorCode
     *
     * @return the value of t_Evt_EventDefine.FDelayCalculatorCode
     *
     * @mbg.generated
     */
    public String getDelayCalculatorCode() {
        return delayCalculatorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FDelayCalculatorCode
     *
     * @param delayCalculatorCode the value for t_Evt_EventDefine.FDelayCalculatorCode
     *
     * @mbg.generated
     */
    public void setDelayCalculatorCode(String delayCalculatorCode) {
        this.delayCalculatorCode = delayCalculatorCode == null ? null : delayCalculatorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FLastModifyTime
     *
     * @return the value of t_Evt_EventDefine.FLastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FLastModifyTime
     *
     * @param lastModifyTime the value for t_Evt_EventDefine.FLastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_Evt_EventDefine.FRetry
     *
     * @return the value of t_Evt_EventDefine.FRetry
     *
     * @mbg.generated
     */
    public Integer getRetry() {
        return retry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_Evt_EventDefine.FRetry
     *
     * @param retry the value for t_Evt_EventDefine.FRetry
     *
     * @mbg.generated
     */
    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EventDefine other = (EventDefine) that;
        return (this.getID() == null ? other.getID() == null : this.getID().equals(other.getID()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getBizDefineId() == null ? other.getBizDefineId() == null : this.getBizDefineId().equals(other.getBizDefineId()))
            && (this.getBizDefineName() == null ? other.getBizDefineName() == null : this.getBizDefineName().equals(other.getBizDefineName()))
            && (this.getBizDefineCode() == null ? other.getBizDefineCode() == null : this.getBizDefineCode().equals(other.getBizDefineCode()))
            && (this.getNodeDefineId() == null ? other.getNodeDefineId() == null : this.getNodeDefineId().equals(other.getNodeDefineId()))
            && (this.getNodeDefineName() == null ? other.getNodeDefineName() == null : this.getNodeDefineName().equals(other.getNodeDefineName()))
            && (this.getNodeDefineCode() == null ? other.getNodeDefineCode() == null : this.getNodeDefineCode().equals(other.getNodeDefineCode()))
            && (this.getTopic() == null ? other.getTopic() == null : this.getTopic().equals(other.getTopic()))
            && (this.getSubscribe() == null ? other.getSubscribe() == null : this.getSubscribe().equals(other.getSubscribe()))
            && (this.getProducerGroupName() == null ? other.getProducerGroupName() == null : this.getProducerGroupName().equals(other.getProducerGroupName()))
            && (this.getConsumerGroupName() == null ? other.getConsumerGroupName() == null : this.getConsumerGroupName().equals(other.getConsumerGroupName()))
            && (this.getIsEnabled() == null ? other.getIsEnabled() == null : this.getIsEnabled().equals(other.getIsEnabled()))
            && (this.getStep() == null ? other.getStep() == null : this.getStep().equals(other.getStep()))
            && (this.getDelayType() == null ? other.getDelayType() == null : this.getDelayType().equals(other.getDelayType()))
            && (this.getDelayLevel() == null ? other.getDelayLevel() == null : this.getDelayLevel().equals(other.getDelayLevel()))
            && (this.getDelayCalculatorCode() == null ? other.getDelayCalculatorCode() == null : this.getDelayCalculatorCode().equals(other.getDelayCalculatorCode()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getRetry() == null ? other.getRetry() == null : this.getRetry().equals(other.getRetry()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getID() == null) ? 0 : getID().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getBizDefineId() == null) ? 0 : getBizDefineId().hashCode());
        result = prime * result + ((getBizDefineName() == null) ? 0 : getBizDefineName().hashCode());
        result = prime * result + ((getBizDefineCode() == null) ? 0 : getBizDefineCode().hashCode());
        result = prime * result + ((getNodeDefineId() == null) ? 0 : getNodeDefineId().hashCode());
        result = prime * result + ((getNodeDefineName() == null) ? 0 : getNodeDefineName().hashCode());
        result = prime * result + ((getNodeDefineCode() == null) ? 0 : getNodeDefineCode().hashCode());
        result = prime * result + ((getTopic() == null) ? 0 : getTopic().hashCode());
        result = prime * result + ((getSubscribe() == null) ? 0 : getSubscribe().hashCode());
        result = prime * result + ((getProducerGroupName() == null) ? 0 : getProducerGroupName().hashCode());
        result = prime * result + ((getConsumerGroupName() == null) ? 0 : getConsumerGroupName().hashCode());
        result = prime * result + ((getIsEnabled() == null) ? 0 : getIsEnabled().hashCode());
        result = prime * result + ((getStep() == null) ? 0 : getStep().hashCode());
        result = prime * result + ((getDelayType() == null) ? 0 : getDelayType().hashCode());
        result = prime * result + ((getDelayLevel() == null) ? 0 : getDelayLevel().hashCode());
        result = prime * result + ((getDelayCalculatorCode() == null) ? 0 : getDelayCalculatorCode().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getRetry() == null) ? 0 : getRetry().hashCode());
        return result;
    }
}