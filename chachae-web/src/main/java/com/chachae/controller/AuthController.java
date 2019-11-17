package com.chachae.controller;

import com.chachae.common.core.bean.Result;
import com.chachae.common.core.entity.bo.User;
import com.chachae.common.security.service.AuthService;
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
    return Result.ok(token);
  }
}
