package com.chachae.dao;

import com.chachae.entity.bo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author chachae
 * @date 2019/11/14 21:31
 */
public interface AuthDao extends Mapper<User> {

  /**
   * 通过邮箱地址获取用户登录信息
   *
   * @param username 邮箱
   * @return 用户登录信息
   */
  User queryByEmail(@Param("email") String username);
}
