package com.yl.study;

/**
 * @author yaoliao
 * @date 2018/11/6
 * @time 22:42
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public enum StateEnum {
    SUCCESS(1),
    FAILED(2);

    StateEnum(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public static void main(String[] args) {
        StateEnum success = Enum.valueOf(StateEnum.class, "SUCCESS");
        System.out.println(success.getValue());
    }
}
