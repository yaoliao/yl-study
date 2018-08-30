package com.yl.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 18:56
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class BaseResponse<T> {

    private Integer code;
    private String message;
    private String reqNo;

    private T dateBody;

    private BaseResponse(T dateBody) {
        this.dateBody = dateBody;
    }

    private BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(Integer code, String message, T dateBody) {
        this.code = code;
        this.message = message;
        this.dateBody = dateBody;
    }

    private BaseResponse(Integer code, String message, String reqNo, T dateBody) {
        this.code = code;
        this.message = message;
        this.reqNo = reqNo;
        this.dateBody = dateBody;
    }

    public static <T> BaseResponse<T> createSuccess(T data, String msg) {
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtils.isNotBlank(msg) ? msg : StatusEnum.SUCCESS.getMsg(), data);
    }

    public static <T> BaseResponse<T> createSuccess(T data) {
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMsg(), data);
    }

    public static <T> BaseResponse<T> createSuccess() {
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public T getDateBody() {
        return dateBody;
    }

    public void setDateBody(T dateBody) {
        this.dateBody = dateBody;
    }
}
