package com.chachae.service.impl;

import com.chachae.dao.RoleDao;
import com.chachae.entity.bo.Role;
import com.chachae.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:50
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Resource private RoleDao roleDao;

  @Override
  public List<Role> queryAll() {
    return this.roleDao.selectAll();
  }
}
