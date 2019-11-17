package com.chachae.security.jwt;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.chachae.core.enums.REnum;
import com.chachae.core.exception.ApiException;
import com.chachae.core.utils.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chachae
 * @date 2019/11/16 12:13
 */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

  @Override
  protected boolean isAccessAllowed(
      ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
    HttpServletResponse response = WebUtils.toHttp(servletResponse);
    if (!isLoginAttempt(servletRequest, servletResponse)) {
      writerResponse(response, REnum.NOT_SING_IN.val, "无身份认证权限标示");
      return false;
    }
    try {
      return executeLogin(servletRequest, servletResponse);
    } catch (ApiException e) {
      writerResponse(response, e.getCode(), e.getMsg());
      return false;
    }
  }

  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) {
    Subject subject = SecurityUtils.getSubject();
    JwtToken token = new JwtToken();
    token.setToken(this.getAuthorization(request));
    try {
      subject.login(token);
    } catch (DisabledAccountException e) {
      throw ApiException.builder().code(REnum.NOT_SING_IN.val).msg(e.getMessage()).build();
    } catch (Exception e) {
      throw ApiException.systemError(e.getMessage());
    }
    return true;
  }

  /** 判断用户是否想要登入。 检测header里面是否包含Authorization字段即可 */
  @Override
  protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    return StrUtil.isNotBlank(this.getAuthorization(request));
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
    return false;
  }

  /** 获取token */
  private String getAuthorization(ServletRequest request) {
    HttpServletRequest req = (HttpServletRequest) request;
    return req.getHeader("Authorization");
  }

  /** 输出 */
  private void writerResponse(HttpServletResponse response, long code, String msg) {
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    try {
      Dict dict = Dict.create().set("ret", false).set("status", code).set("msg", msg);
      response.getWriter().write(JsonUtil.obj2String(dict));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
