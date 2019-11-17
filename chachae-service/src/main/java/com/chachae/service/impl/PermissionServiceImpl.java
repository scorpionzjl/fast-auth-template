package com.chachae.service.impl;

import com.chachae.common.core.entity.bo.Permission;
import com.chachae.dao.PermissionDao;
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
  public List<Permission> queryByUuid(String uuid) {
    return this.permissionDao.queryByUuid(uuid);
  }
}
