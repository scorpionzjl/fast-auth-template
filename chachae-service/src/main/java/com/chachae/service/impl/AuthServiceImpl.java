package com.chachae.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.chachae.common.core.entity.bo.Permission;
import com.chachae.common.core.entity.bo.User;
import com.chachae.common.core.entity.dto.UserDTO;
import com.chachae.common.core.exception.ApiException;
import com.chachae.common.redis.service.CacheService;
import com.chachae.common.security.jwt.JwtToken;
import com.chachae.common.security.service.AuthService;
import com.chachae.dao.AuthDao;
import com.chachae.dao.PermissionDao;
import com.google.common.collect.Sets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 认证业务实现
 *
 * @author chachae
 * @date 2019/11/14 21:30
 */
@Service
public class AuthServiceImpl implements AuthService {

  @Resource private AuthDao authDao;
  @Resource private PermissionDao permissionDao;
  @Resource private CacheService cacheService;

  @Override
  public Map<String, Object> login(UserDTO dto) {
    Subject subject = SecurityUtils.getSubject();
    JwtToken token = new JwtToken();
    token.setUsername(dto.getUsername());
    token.setPassword(dto.getPassword());
    try {
      subject.login(token);
    } catch (Exception e) {
      throw ApiException.argError(e.getMessage());
    }
    String value = ((JwtToken) SecurityUtils.getSubject().getPrincipal()).getToken();
    return Dict.create().set("token", value);
  }

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
    return this.authDao.selectByExample(example).get(0).getRole();
  }

  @Override
  public Set<String> queryPermissionsByUuid(String uuid) {
    // 尝试从redis 中获取权限
    Set<String> sets = this.cacheService.getPermission(uuid);
    if (ObjectUtil.isNotEmpty(sets)) {
      return sets;
    } else {
      List<Permission> list = this.permissionDao.queryByUuid(uuid);
      Set<String> set = Sets.newHashSet();
      list.forEach(pms -> set.add(pms.getPermission()));
      this.cacheService.setPermission(uuid, set);
      return set;
    }
  }
  /**
   * 邮箱地址的正则表达式验证
   *
   * @param username 用户名
   * @return true / false
   */
  private boolean isEmail(@Valid String username) {
    String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    return username.matches(email);
  }
}
