package com.chachae.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.chachae.dao.AuthDao;
import com.chachae.dao.PermissionDao;
import com.chachae.entity.bo.Permission;
import com.chachae.entity.bo.User;
import com.chachae.service.AuthService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author chachae
 * @date 2019/11/14 21:30
 */
@Service
public class AuthServiceImpl implements AuthService {

  @Resource private AuthDao authDao;
  @Resource private PermissionDao permissionDao;

  @Override
  public User queryUserUsername(String username) {
    if (StrUtil.isBlank(username)) {
      return null;
    }
    // 邮箱登录
    if (isEmail(username)) {
      return this.authDao.queryByEmail(username);
    } else {
      // 用户名登陆
      Example example = new Example(User.class);
      Example.Criteria criteria = example.createCriteria();
      criteria.andEqualTo("username", username);
      List<User> list = this.authDao.selectByExample(example);
      if (ObjectUtil.isNotEmpty(list)) {
        // 用户名唯一，get(0) 可以获取准确的用户登录信息
        return list.get(0);
      }
    }
    return null;
  }

  @Override
  public Integer queryRoleByUuid(String uuid) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("uuid", uuid);
    User user = this.authDao.selectByExample(example).get(0);
    return user.getRole();
  }

  @Override
  public Set<String> queryPermissionsByUuid(String uuid) {
    List<Permission> list = this.permissionDao.queryByUuid(uuid);
    Set<String> set = Sets.newHashSet();
    list.forEach(pms -> set.add(pms.getPermission()));
    return set;
  }

  /**
   * 邮箱地址的正则表达式验证
   *
   * @param username 用户名
   * @return true / false
   */
  private boolean isEmail(String username) {
    String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    return username.matches(email);
  }
}
