package com.chachae.controller;

import com.chachae.common.core.bean.Result;
import com.chachae.common.core.entity.dto.UserDTO;
import com.chachae.common.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/11/14 21:20
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "用户认证模块")
public class AuthController {

  @Resource private AuthService authService;

  @PostMapping("/login")
  @ApiOperation(value = "认证获取token", httpMethod = "POST", produces = "application/json")
  public Result auth(@Valid UserDTO dto) {
    Map<String, Object> result = authService.login(dto);
    return Result.ok(result);
  }
}
