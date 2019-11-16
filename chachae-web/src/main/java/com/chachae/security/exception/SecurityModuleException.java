package com.chachae.security.exception;

/**
 * @author chachae
 * @date 2019/11/16 14:56
 */
public class SecurityModuleException extends RuntimeException {

  private static final long serialVersionUID = -3140935162042489289L;

  public SecurityModuleException() {
    super();
  }

  public SecurityModuleException(String message) {
    super(message);
  }

  public SecurityModuleException(String message, Throwable cause) {
    super(message, cause);
  }

  public SecurityModuleException(Throwable cause) {
    super(cause);
  }

  protected SecurityModuleException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
