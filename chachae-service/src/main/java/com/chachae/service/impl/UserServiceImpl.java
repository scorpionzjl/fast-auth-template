package com.chachae.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.chachae.core.entity.bo.User;
import com.chachae.core.entity.bo.UserInfo;
import com.chachae.core.exception.ApiException;
import com.chachae.dao.UserDao;
import com.chachae.dao.UserInfoDao;
import com.chachae.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:08
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource private UserDao userDao;
  @Resource private UserInfoDao userInfoDao;

  @Override
  public List<User> queryAll() {
    return this.userDao.selectAll();
  }

  @Override
  public void deleteByUuid(String uuid) {
    this.userDao.deleteByPrimaryKey(uuid);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(User user) {
    this.userDao.updateByPrimaryKeySelective(user);
  }

  @Override
  public void add(User user) {
    if (isExist(user.getUsername())) {
      throw ApiException.argError("用户名已存在！");
    }
    // 创建uuid
    String uuid = IdUtil.simpleUUID();
    user.setUuid(uuid);
    this.userDao.insertSelective(user);
    UserInfo bo = new UserInfo();
    bo.setUuid(uuid);
    this.userInfoDao.insertSelective(bo);
  }

  @Override
  public User queryByPrimaryKey(String uuid) {
    return this.userDao.selectByPrimaryKey(uuid);
  }

  /**
   * 检查用户名是否已经存在
   *
   * @param username 用户名
   * @return boolean
   */
  private boolean isExist(String username) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("username", username);
    List<User> users = this.userDao.selectByExample(example);
    return ObjectUtil.isNotEmpty(users);
  }
}
