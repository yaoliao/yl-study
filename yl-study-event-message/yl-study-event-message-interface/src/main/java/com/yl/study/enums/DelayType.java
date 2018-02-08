package com.yl.study.enums;

/**
 * Created by DELL on 2017/11/2.
 */
public enum DelayType {

    NONE("不延迟", 0),
    FIXED_DELAY_LEVEL("固定延时级别", 1),
    CUSTOM("自定义延时级别", 2);

    private String content;
    private Integer value;

    DelayType(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static DelayType getByValue(Integer value) {
        DelayType[] entities = DelayType.values();
        for (DelayType entity : entities) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
