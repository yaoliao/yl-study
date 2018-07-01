package com.yl.study.example19;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public enum ClassEnum {

    CLASS_1("教室1", 1),
    CLASS_2("教室2", 2),
    CLASS_3("教室3", 3);

    private String value;
    private Integer key;

    ClassEnum(String val, Integer key) {
        this.value = val;
        this.key = key;
    }

    public static ClassEnum valueof(Integer key) {
        return Arrays.stream(ClassEnum.values()).filter(v -> v.getKey().equals(key))
                .findFirst().orElse(null);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
