package com.chachae.exception;

/**
 * @author chachae
 * @date 2019/11/16 14:32
 */
public class ControllerException extends RuntimeException {

    private static final long serialVersionUID = -3140935162042489289L;

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    protected ControllerException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}