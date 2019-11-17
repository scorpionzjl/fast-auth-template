package com.chachae.dao;

import com.chachae.core.entity.bo.UserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户详细信息DAO
 *
 * @author chachae
 * @date 2019/11/13 15:04
 */
public interface UserInfoDao extends Mapper<UserInfo> {

  /**
   * 联表查询用户详细信息
   *
   * @param uuid uuid
   * @return 所有用户详细信息
   */
  UserInfo queryByUuid(@Param("uuid") String uuid);

  /**
   * 联表查询全部
   *
   * @return 所有用户详细信息
   */
  List<UserInfo> queryAll();

  /**
   * 模糊查询
   *
   * @param name 姓名
   * @param departmentId 部门id
   * @return 模糊条件对应成员信息
   */
  List<UserInfo> fuzzyQuery(@Param("name") String name, @Param("deptId") Integer departmentId);
}
