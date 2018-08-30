package com.yl.utils;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 18:59
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public enum StatusEnum {

    /**
     * 成功
     */
    SUCCESS(9000, "成功"),
    /**
     * 成功
     */
    FALLBACK(8000, "FALL_BACK"),
    /**
     * 参数校验失败
     **/
    VALIDATION_FAIL(3000, "参数校验失败"),
    /**
     * 失败
     */
    FAIL(4000, "失败"),

    /**
     * 重复请求
     */
    REPEAT_REQUEST(5000, "重复请求"),

    /**
     * 请求限流
     */
    REQUEST_LIMIT(6000, "请求限流");

    private String msg;
    private Integer code;

    StatusEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public StatusEnum valueOf(Integer code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
