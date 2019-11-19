package com.chachae.common.redis.service;

import java.util.Set;

/**
 * redis 缓存接口
 *
 * @author chachae
 * @date 2019/11/19 11:27
 */
public interface CacheService {

  /**
   * 获取缓存
   *
   * @param uuid uuid
   * @return 缓存信息
   */
  Set<String> getPermission(String uuid);

  /**
   * 设置缓存
   *
   * @param uuid uuid
   * @param values 值
   */
  void setPermission(String uuid, Set<String> values);
}
