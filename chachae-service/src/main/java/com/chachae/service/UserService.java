package com.chachae.service;

import com.chachae.common.core.entity.bo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:06
 */
public interface UserService {

  /**
   * 获取用户列表
   *
   * @return List
   */
  List<User> queryAll();

  /**
   * 通过uuid 删除用户信息
   *
   * @param uuid uuid
   */
  void deleteByUuid(String uuid);

  /**
   * 更新用户登录信息
   *
   * @param user 用户登陆信息
   */
  @Transactional(rollbackFor = Exception.class)
  void update(User user);

  /**
   * 增加用户登录信息
   *
   * @param user 用户登陆信息
   */
  void add(User user);

  /**
   * 通过uuid 查询此用户的登录信息
   *
   * @param uuid uuid
   * @return 此用户的登录信息
   */
  User queryByPrimaryKey(String uuid);
}
