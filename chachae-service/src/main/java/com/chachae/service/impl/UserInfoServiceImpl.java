package com.chachae.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.chachae.common.core.entity.bo.UserInfo;
import com.chachae.common.core.entity.vo.UserInfoVO;
import com.chachae.common.core.exception.ApiException;
import com.chachae.common.core.utils.BeanCopyUtil;
import com.chachae.dao.UserInfoDao;
import com.chachae.service.UserInfoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:08
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Resource private UserInfoDao userInfoDao;

  @Override
  public void update(UserInfo userInfo) {
    Integer id = userInfo.getId();
    String email = userInfo.getEmail();
    // 先判断是否有填写邮箱
    if (ObjectUtil.isNotEmpty(email)) {
      if (isEmailExist(id, email)) {
        throw ApiException.argError("邮箱已被注册");
      }
    }
    this.userInfoDao.updateByPrimaryKeySelective(userInfo);
  }

  @Override
  public List<UserInfoVO> queryAll() {
    List<UserInfo> boList = this.userInfoDao.queryAll();
    return commonResultTemplate(boList);
  }

  @Override
  public List<UserInfoVO> fuzzyQuery(String name, Integer departmentId) {
    List<UserInfo> boList = this.userInfoDao.fuzzyQuery(name, departmentId);
    return commonResultTemplate(boList);
  }

  @Override
  public UserInfoVO queryByUuid(String uuid) {
    UserInfo boList = this.userInfoDao.queryByUuid(uuid);
    UserInfoVO vo = BeanCopyUtil.copyObject(boList, UserInfoVO.class);
    assert vo != null;
    vo.setName(boList.getName());
    vo.setRole(boList.getUser().getRole());
    vo.setUsername(boList.getUser().getUsername());
    return vo;
  }

  private List<UserInfoVO> commonResultTemplate(List<UserInfo> boList) {
    List<UserInfoVO> voList = Lists.newArrayList();
    for (UserInfo userInfo : boList) {
      UserInfoVO vo = BeanCopyUtil.copyObject(userInfo, UserInfoVO.class);
      assert vo != null;
      vo.setName(userInfo.getName());
      vo.setRole(userInfo.getUser().getRole());
      vo.setUsername(userInfo.getUser().getUsername());
      voList.add(vo);
    }
    return voList;
  }

  /**
   * 检查用户名是否已经存在
   *
   * @param email 用户名
   * @return boolean
   */
  private boolean isEmailExist(Integer id, String email) {
    // 先检查是否和自己的一样。一样则允许重复更新
    UserInfo info = this.userInfoDao.selectByPrimaryKey(id);
    if (StrUtil.isEmpty(info.getEmail())) {
      Example example = new Example(UserInfo.class);
      Example.Criteria criteria = example.createCriteria();
      criteria.andEqualTo("email", email);
      List<UserInfo> list = this.userInfoDao.selectByExample(example);
      return ObjectUtil.isNotEmpty(list);
    }
    return !info.getEmail().equals(email);
  }
}
