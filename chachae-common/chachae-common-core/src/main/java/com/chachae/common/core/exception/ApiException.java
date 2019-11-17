package com.chachae.common.core.exception;

import com.chachae.common.core.enums.REnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/11/17 12:40
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 响应状态回执码 */
  protected Integer code;

  /** 响应回执消息 */
  protected String msg;

  /** 异常信息 */
  private Exception ex;

  /**
   * 参数异常
   *
   * @param msg 异常消息
   * @return 异常通知
   */
  public static ApiException argError(String msg) {
    return ApiException.builder().code(REnum.ARG_ERROR.val).msg(msg).build();
  }

  /**
   * 系统异常
   *
   * @param msg 异常消息
   * @return 异常通知
   */
  public static ApiException systemError(String msg) {
    return ApiException.builder().code(REnum.SYSTEM_ERROR.val).msg(msg).build();
  }
}
