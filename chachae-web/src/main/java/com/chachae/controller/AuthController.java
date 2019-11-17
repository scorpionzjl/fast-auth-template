package com.chachae.controller;

import com.chachae.common.Result;
import com.chachae.core.entity.bo.User;
import com.chachae.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
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
  public Result auth(User user) {
    String token = authService.login(user);
    return Result.success("登录成功", token);
  }
}
