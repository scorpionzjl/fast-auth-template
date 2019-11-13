package com.chachae.exception;

/**
 * 自定义异常处理<br>
 * 继承了RuntimeException，将错误交给了GlobalExceptionResolver处理
 *
 * @author chachae
 * @date 2019/11/13 15:45
 */
public class PermissionException extends RuntimeException {

  private static final long serialVersionUID = -3140935162042489289L;

  public PermissionException() {
    super();
  }

  public PermissionException(String message) {
    super(message);
  }

  public PermissionException(String message, Throwable cause) {
    super(message, cause);
  }

  public PermissionException(Throwable cause) {
    super(cause);
  }

  protected PermissionException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
