package com.chachae.controller;

import cn.hutool.core.util.ObjectUtil;
import com.chachae.common.Result;
import com.chachae.core.entity.bo.User;
import com.chachae.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户登陆信息控制层
 *
 * @author chachae
 * @date 2019/11/12 22:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource private UserService userService;

  /**
   * 登录信息列表
   *
   * @return 登录信息
   */
  @GetMapping("/list")
  public Result list() {
    List<User> userList = this.userService.queryAll();
    if (ObjectUtil.isNotEmpty(userList)) {
      return Result.success("获取成功", userList);
    }
    return Result.success("没有数据");
  }

  /**
   * 通过uuid 获取用户登录信息
   *
   * @param uuid uuid
   * @return 该用户的登录信息
   */
  @GetMapping("/query/{uuid}")
  public Result getByUuid(@PathVariable String uuid) {
    User user = this.userService.queryByPrimaryKey(uuid);
    // 去除密码等敏感信息
    user.setPassword(null);
    return Result.success("获取成功", user);
  }

  /**
   * 删除用户登录信息
   *
   * @param uuid uuid
   * @return 删除情况 todo 权限校验
   */
  @DeleteMapping("/delete/{uuid}")
  public Result delete(@PathVariable String uuid) {
    this.userService.deleteByUuid(uuid);
    return Result.success("删除成功");
  }

  /**
   * 更新用户登录信息
   *
   * @param user 用户信息
   * @return 更新情况 todo 1. 权限校验 2. 密码加密
   */
  @PutMapping("/update")
  public Result delete(@Valid User user) {
    this.userService.update(user);
    return Result.success("更新成功");
  }

  /**
   * 增加用户登录信息
   *
   * @param user 用户登陆信息
   * @return 增加情况 todo 1. 权限校验 2. 密码校验
   */
  @PostMapping("/add")
  public Result add(@Valid User user) {
    this.userService.add(user);
    return Result.success("增加成功");
  }
}
