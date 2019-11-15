package com.chachae.service.impl;

import com.chachae.dao.PermissionDao;
import com.chachae.entity.bo.Permission;
import com.chachae.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:30
 */
@Service
public class PermissionServiceImpl implements PermissionService {

  @Resource private PermissionDao permissionDao;

  @Override
  public List<Permission> queryByRoleId(Integer roleId) {
    return this.permissionDao.queryByRoleId(roleId);
  }
}
