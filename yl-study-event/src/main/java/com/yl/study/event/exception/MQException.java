package com.yl.study.event.exception;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class MQException extends EventException {

    private static final long serialVersionUID = 2400011244203171655L;

    public MQException() {
        super();
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(Throwable cause) {
        super(cause);
    }

    protected MQException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
