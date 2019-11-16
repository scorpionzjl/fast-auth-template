package com.chachae.test.controller;

import lombok.AllArgsConstructor;

/**
 * @author chachae
 * @date 2019/11/16 21:54
 */
@AllArgsConstructor
public enum REnum {
  SUCCESS(200, "成功"),
  FAIL(201, "操作失败"),
  SYSTEM_ERROR(202, "系统异常"),
  NOT_SING_IN(203, "用户未登录或身份异常"),
  ARG_ERROR(400, "参数错误"),
  UN_AUTHORIZED(401, "权限不足");

  public final Integer val;

  public final String desc;
}
