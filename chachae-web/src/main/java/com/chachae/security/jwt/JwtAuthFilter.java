package com.chachae.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.chachae.security.exception.SecurityModuleException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chachae
 * @date 2019/11/16 12:13
 */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

  @Override
  protected boolean isAccessAllowed(
      ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
    if (!isLoginAttempt(servletRequest, servletResponse)) {
      return false;
    }
    return executeLogin(servletRequest, servletResponse);
  }

  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) {
    Subject subject = SecurityUtils.getSubject();
    JwtToken token = new JwtToken();
    token.setToken(this.getAuthorization(request));
    subject.login(token);
    try {
      subject.login(token);
    } catch (DisabledAccountException e) {
      throw new SecurityModuleException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException("系统异常");
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
}
