package com.chachae.service;

import com.chachae.core.entity.bo.Role;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:50
 */
public interface RoleService {

  /**
   * 获取角色列表
   *
   * @return 角色列表信息
   */
  List<Role> queryAll();
}
