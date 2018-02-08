package com.yl.study.event.rocketmq.processor;


import com.yl.study.bo.ExtraConfiguration;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class MessageContext {

    /**
     * 业务定义Key
     */
    private String bizDefindCode;

    /**
     * 节点定义Key
     */
    private String nodeDefindCode;

    /**
     * 业务主ID
     */
    private Integer bizPrimaryId;

    /**
     * 自定义扩展内容,格式自由
     */
    private String extend;

    /**
     * 标准化额外配置
     */
    private ExtraConfiguration extraConfiguration;

    public MessageContext() {
    }

    public MessageContext(String bizDefindCode, String nodeDefindCode, Integer bizPrimaryId,
                          String extend, ExtraConfiguration extraConfiguration) {
        this.bizDefindCode = bizDefindCode;
        this.nodeDefindCode = nodeDefindCode;
        this.bizPrimaryId = bizPrimaryId;
        this.extend = extend;
        this.extraConfiguration = extraConfiguration;
    }

    public String getBizDefindCode() {
        return bizDefindCode;
    }

    public void setBizDefindCode(String bizDefindCode) {
        this.bizDefindCode = bizDefindCode;
    }

    public String getNodeDefindCode() {
        return nodeDefindCode;
    }

    public void setNodeDefindCode(String nodeDefindCode) {
        this.nodeDefindCode = nodeDefindCode;
    }

    public Integer getBizPrimaryId() {
        return bizPrimaryId;
    }

    public void setBizPrimaryId(Integer bizPrimaryId) {
        this.bizPrimaryId = bizPrimaryId;
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

    @Override
    public String toString() {
        return "MessageContext{" +
                "bizDefindCode='" + bizDefindCode + '\'' +
                ", nodeDefindCode='" + nodeDefindCode + '\'' +
                ", bizPrimaryId=" + bizPrimaryId +
                ", extend='" + extend + '\'' +
                '}';
    }

}
