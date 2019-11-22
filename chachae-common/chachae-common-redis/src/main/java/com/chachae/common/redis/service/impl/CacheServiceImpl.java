package com.chachae.common.redis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.chachae.common.core.utils.JsonUtil;
import com.chachae.common.redis.constant.RedisConsts;
import com.chachae.common.redis.service.CacheService;
import com.chachae.common.redis.utils.RedisUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author chachae
 * @date 2019/11/19 11:28
 */
@Service
public class CacheServiceImpl implements CacheService {

  @Override
  public Set<String> getPermission(String uuid) {
    String key = RedisConsts.CACHE_PERMISSION_PREFIX + uuid;
    String values = RedisUtil.get(key);
    if (StrUtil.isNotBlank(values)) {
      return JsonUtil.string2Obj(values, new TypeReference<Set<String>>() {});
    }
    return null;
  }

  @Override
  public void setPermission(String uuid, Set<String> values) {
    String key = RedisConsts.CACHE_PERMISSION_PREFIX + uuid;
    RedisUtil.set(key, JsonUtil.obj2String(values), RedisConsts.EXPIRE_TIME);
  }
}
