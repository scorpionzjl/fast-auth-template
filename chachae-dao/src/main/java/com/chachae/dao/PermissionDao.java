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
   * @param uuid 用户uuid
   * @return 角色权限信息
   */
  List<Permission> queryByUuid(@Param("uuid") String uuid);
}
