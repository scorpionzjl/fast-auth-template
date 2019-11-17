package com.chachae.security.service;

import com.chachae.core.entity.bo.User;
import com.chachae.security.jwt.JwtToken;

import java.util.Set;

/**
 * @author chachae
 * @date 2019/11/14 21:30
 */
public interface AuthService {

  String login(User user);

  /**
   * 根据帐号获取登录信息
   *
   * @param username 帐号
   * @return User
   */
  User queryUserUsername(String username);

  /**
   * 根据id获取角色信息
   *
   * @param uuid 用户uuid
   * @return integer
   */
  Integer queryRoleByUuid(String uuid);

  /**
   * 根据id获取权限信息
   *
   * @param uuid 用户uuid
   * @return java.util.Set<java.lang.String>
   */
  Set<String> queryPermissionsByUuid(String uuid);
}
