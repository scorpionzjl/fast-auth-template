package com.chachae.controller;

import com.chachae.common.Result;
import com.chachae.exception.ControllerException;
import com.chachae.security.jwt.JwtToken;
import com.chachae.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/11/14 21:20
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Resource private AuthService authService;

  @PostMapping("/login")
  public Result auth(String username, String password) {
    Subject subject = SecurityUtils.getSubject();
    JwtToken jwtToken = new JwtToken();
    jwtToken.setUsername(username);
    jwtToken.setPassword(password);
    try {
      subject.login(jwtToken);
    } catch (Exception e) {
      throw new ControllerException(e.getMessage());
    }
    String token = ((JwtToken) SecurityUtils.getSubject().getPrincipal()).getToken();
    return Result.success("登录成功", token);
  }
}
