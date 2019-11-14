package com.chachae.exception;

/**
 * @author chachae
 * @date 2019/11/14 15:58
 */
public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = -3140935162042489289L;

  public ServiceException() {
    super();
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  protected ServiceException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
