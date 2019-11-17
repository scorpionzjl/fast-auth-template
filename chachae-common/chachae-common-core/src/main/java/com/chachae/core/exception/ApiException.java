package com.chachae.core.exception;

import com.chachae.core.enums.REnum;
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
  protected long code;

  /** 响应回执消息 */
  protected String msg;

  /** 异常信息 */
  private Exception ex;

  public static ApiException argError(String msg) {
    return ApiException.builder().code(REnum.ARG_ERROR.val).msg(msg).build();
  }

  public static ApiException systemError(String msg) {
    return ApiException.builder().code(REnum.SYSTEM_ERROR.val).msg(msg).build();
  }
}
