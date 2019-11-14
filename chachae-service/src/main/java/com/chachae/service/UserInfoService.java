package com.chachae.service;

import com.chachae.entity.bo.UserInfo;
import com.chachae.entity.vo.UserInfoVO;
import com.chachae.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/13 15:06
 */
public interface UserInfoService {

  /**
   * 获取用户列表
   *
   * @return List
   */
  List<UserInfoVO> queryAll();

  /**
   * 更新用户登录信息
   *
   * @param userInfo 用户登陆信息
   */
  void update(UserInfo userInfo);

  /**
   * 通过uuid 查询此用户的登录信息
   *
   * @param uuid uuid
   * @return 此用户的登录信息
   */
  UserInfoVO queryByUuid(String uuid);

  /**
   * 模糊查询
   *
   * @param name 姓名
   * @param departmentId 部门id
   * @return 对应条件下的用户详细信息
   */
  List<UserInfoVO> fuzzyQuery(String name, Integer departmentId);
}
