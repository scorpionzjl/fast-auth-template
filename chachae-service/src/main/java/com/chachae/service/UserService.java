package com.chachae.service;

import com.chachae.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/12 22:06
 */
public interface UserService {

  /**
   * 获取用户列表
   *
   * @return
   */
  List<User> getList();
}
