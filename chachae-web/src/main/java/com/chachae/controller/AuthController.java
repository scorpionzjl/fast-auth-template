package com.chachae.controller;

import com.chachae.common.Result;
import com.chachae.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chachae
 * @date 2019/11/14 21:20
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Resource private AuthService authService;

  @PostMapping("/login")
  public Result auth(String username, String password) {
    return Result.success();
  }
}
