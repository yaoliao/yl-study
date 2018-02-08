package com.yl.study.event.exception;

/**
 * 事件模式异常基类
 *
 * @author DELL
 * @date 2017/10/30
 */
public class EventException extends Exception {

    private static final long serialVersionUID = 7250874129215146582L;

    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

    protected EventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
