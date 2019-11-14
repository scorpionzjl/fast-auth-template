package com.chachae.service.impl;

import com.chachae.dao.UserInfoDao;
import com.chachae.entity.bo.UserInfo;
import com.chachae.entity.vo.UserInfoVO;
import com.chachae.service.UserInfoService;
import com.chachae.utils.BeanCopyUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

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
    vo.setName(boList.getName());
    vo.setRole(boList.getUser().getRole());
    vo.setUsername(boList.getUser().getUsername());
    return vo;
  }

  private List<UserInfoVO> commonResultTemplate(List<UserInfo> boList) {
    List<UserInfoVO> voList = Lists.newArrayList();
    for (UserInfo userInfo : boList) {
      UserInfoVO vo = BeanCopyUtil.copyObject(userInfo, UserInfoVO.class);
      vo.setName(userInfo.getName());
      vo.setRole(userInfo.getUser().getRole());
      vo.setUsername(userInfo.getUser().getUsername());
      voList.add(vo);
    }
    return voList;
  }
}
