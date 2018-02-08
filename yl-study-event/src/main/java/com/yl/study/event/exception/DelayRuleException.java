package com.yl.study.event.exception;

/**
 * 延时级别异常基类
 *
 * @author DELL
 * @date 2017/10/30
 */
public class DelayRuleException extends Exception {

    private static final long serialVersionUID = 4074382626478932030L;

    public DelayRuleException() {
        super();
    }

    public DelayRuleException(String message) {
        super(message);
    }

    public DelayRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelayRuleException(Throwable cause) {
        super(cause);
    }

    protected DelayRuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
