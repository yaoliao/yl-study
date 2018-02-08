package com.yl.study.solr.enums;

/**
 * @author DELL
 * @date 2017/10/26
 */
public enum PositionEnum {

    MONITOR(1, "班长"),
    VICEMONITOR(2, "副班长");

    private Integer value;
    private String content;

    PositionEnum(Integer value, String content) {
        this.value = value;
        this.content = content;
    }

    public static PositionEnum valueOf(Integer value) {
        PositionEnum[] entites = PositionEnum.values();
        for (PositionEnum entity : entites) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

}
