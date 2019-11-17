package com.chachae.common;

import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回
 *
 * @author chachae
 * @date 2019/11/13 9:23
 */
@Data
@NoArgsConstructor
public class Result {

  private static final String DEFAULT_SUCCESS_MESSAGE = "request success!";

  private static final String DEFAULT_FAIL_MESSAGE = "request bad!";

  private boolean ret;

  private Integer status;

  private String msg;

  private Object data;

  private Result(Integer status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public static Result success() {
    Result result = new Result(HttpStatus.HTTP_OK, DEFAULT_SUCCESS_MESSAGE);
    result.ret = true;
    return result;
  }

  public static Result success(String msg) {
    Result result = new Result(HttpStatus.HTTP_OK, msg);
    result.ret = true;
    result.data = null;
    return result;
  }

  public static Result success(String msg, Object data) {
    Result result = new Result(HttpStatus.HTTP_OK, msg);
    result.ret = true;
    result.data = data;
    return result;
  }

  public static Result fail() {
    Result result = new Result(HttpStatus.HTTP_BAD_REQUEST, DEFAULT_FAIL_MESSAGE);
    result.ret = false;
    result.data = null;
    return result;
  }

  public static Result fail(String msg) {
    Result result = new Result(HttpStatus.HTTP_BAD_REQUEST, msg);
    result.ret = false;
    result.data = null;
    return result;
  }

  public static Result fail(String msg, Object data) {
    Result result = new Result(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    result.ret = false;
    result.data = data;
    return result;
  }
}
