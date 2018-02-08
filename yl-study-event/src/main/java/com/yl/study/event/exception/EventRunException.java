package com.yl.study.event.exception;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class EventRunException extends RuntimeException {

    private static final long serialVersionUID = -8421271210927298937L;

    public EventRunException() {
        super();
    }

    public EventRunException(String message) {
        super(message);
    }

    public EventRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventRunException(Throwable cause) {
        super(cause);
    }

    protected EventRunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
