package com.chachae.common.core.bean;

import com.chachae.common.core.enums.REnum;
import com.chachae.common.core.utils.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 统一返回
 *
 * @author chachae
 * @date 2019/11/13 9:23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Result extends BaseEntity {

  /** 响应情况 */
  private boolean ret;

  /** 响应代码 */
  private Integer code;

  /** 响应消息 */
  private String msg;

  /** 响应数据体 */
  private Object data;

  /** 响应时间戳 */
  protected final String timestamps = DateUtil.nowStr();

  private Result(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static Result ok() {
    Result result = new Result(REnum.SUCCESS.val, REnum.SUCCESS.desc);
    result.ret = true;
    return result;
  }

  public static Result ok(Object data) {
    Result result = new Result(REnum.SUCCESS.val, REnum.SUCCESS.desc);
    result.ret = true;
    result.data = data;
    return result;
  }

  public static Result ok(String msg, Object data) {
    Result result = new Result(REnum.SUCCESS.val, msg);
    result.ret = true;
    result.data = data;
    return result;
  }

  public static Result fail() {
    Result result = new Result(REnum.ARG_ERROR.val, REnum.ARG_ERROR.desc);
    result.ret = false;
    result.data = null;
    return result;
  }

  public static Result fail(String msg) {
    Result result = new Result(REnum.ARG_ERROR.val, msg);
    result.ret = false;
    result.data = null;
    return result;
  }

  public static Result fail(String msg, Object data) {
    Result result = new Result(REnum.ARG_ERROR.val, msg);
    result.ret = false;
    result.data = data;
    return result;
  }

  public static Result fail(int code, String msg, Object data) {
    Result result = new Result();
    result.code = code;
    result.ret = false;
    result.msg = msg;
    result.data = data;
    return result;
  }
}
