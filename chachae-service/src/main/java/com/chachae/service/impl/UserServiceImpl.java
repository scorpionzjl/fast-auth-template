package com.chachae.service.impl;

import com.chachae.dao.UserDao;
import com.chachae.entity.User;
import com.chachae.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:08
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource private UserDao userDao;

  @Override
  public List<User> getList() {
    return this.userDao.selectAll();
  }
}
