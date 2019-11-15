package com.chachae.dao;

import com.chachae.entity.bo.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:52
 */
public interface PermissionDao extends Mapper<Permission> {

  /**
   * 通过角色id 查询对应角色下的全部权限
   *
   * @param roleId 角色id
   * @return 角色权限信息
   */
  List<Permission> queryByRoleId(@Param("roleId") Integer roleId);
}
