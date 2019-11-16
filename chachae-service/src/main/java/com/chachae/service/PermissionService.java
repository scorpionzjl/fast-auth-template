package com.chachae.service;

import com.chachae.entity.bo.Permission;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:51
 */
public interface PermissionService {

  /**
   * 通过角色等级查询所有权限信息
   *
   * @param uuid 用户uuid
   * @return 权限信息
   */
  List<Permission> queryByUuid(String uuid);
}
