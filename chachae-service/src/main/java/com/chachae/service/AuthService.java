package com.chachae.service;

import com.chachae.entity.bo.User;

import java.util.Set;

/**
 * @author chachae
 * @date 2019/11/14 21:30
 */
public interface AuthService {

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
