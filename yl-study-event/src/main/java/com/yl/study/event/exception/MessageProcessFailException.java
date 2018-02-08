package com.yl.study.event.exception;

/**
 * @author DELL
 * @date 2017/10/30
 */
public class MessageProcessFailException extends MQException {

    private static final long serialVersionUID = -5847317565997388212L;

    public MessageProcessFailException() {
        super();
    }

    public MessageProcessFailException(String message) {
        super(message);
    }

    public MessageProcessFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageProcessFailException(Throwable cause) {
        super(cause);
    }

    protected MessageProcessFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
