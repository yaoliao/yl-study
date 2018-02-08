package com.yl.study.event.exception;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class ConsumerConfigNotEnoughException extends EventException {

    private static final long serialVersionUID = 7168131482752387580L;


    public ConsumerConfigNotEnoughException() {
        super();
    }

    public ConsumerConfigNotEnoughException(String message) {
        super(message);
    }

    public ConsumerConfigNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerConfigNotEnoughException(Throwable cause) {
        super(cause);
    }

    protected ConsumerConfigNotEnoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
