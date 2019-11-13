package com.chachae.controller;

import com.chachae.entity.User;
import com.chachae.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource private UserService userService;

  @GetMapping("/list")
  public List<User> list() {
    return this.userService.getList();
  }
}
