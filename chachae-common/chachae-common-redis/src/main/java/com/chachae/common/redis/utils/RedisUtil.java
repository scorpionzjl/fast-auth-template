package com.chachae.common.redis.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author chachae
 * @date 2019/11/19 10:30
 */
@Slf4j
@Component
public class RedisUtil {

  /**
   * 同步获取Jedis实例
   *
   * @return Jedis
   */
  private static synchronized Jedis getJedis() {
    return RedisDS.create().getJedis();
  }

  /**
   * 设置 String
   *
   * @param key 键
   * @param value 值
   */
  public static synchronized void set(String key, String value) {
    try {
      value = StrUtil.isBlank(value) ? "" : value;
      Jedis jedis = getJedis();
      jedis.set(key, value);
      jedis.close();
    } catch (Exception e) {
      log.error("Set key error : " + e);
    }
  }

  /**
   * 设置 byte[]
   *
   * @param key 键
   * @param value 值
   */
  public static synchronized void set(byte[] key, byte[] value) {
    try {
      Jedis jedis = getJedis();
      jedis.set(key, value);
      jedis.close();
    } catch (Exception e) {
      log.error("Set key error : " + e);
    }
  }

  /**
   * 设置 String 过期时间
   *
   * @param key 键
   * @param value 值
   * @param seconds 以秒为单位
   */
  public static synchronized void set(String key, String value, int seconds) {
    try {
      value = StrUtil.isBlank(value) ? "" : value;
      Jedis jedis = getJedis();
      jedis.setex(key, seconds, value);
      jedis.close();
    } catch (Exception e) {
      log.error("Set keyex error : " + e);
    }
  }

  /**
   * 设置 byte[] 过期时间
   *
   * @param key 键
   * @param value 值
   * @param seconds 以秒为单位
   */
  public static synchronized void set(byte[] key, byte[] value, int seconds) {
    try {
      Jedis jedis = getJedis();
      jedis.set(key, value);
      jedis.expire(key, seconds);
      jedis.close();
    } catch (Exception e) {
      log.error("Set key error : " + e);
    }
  }

  /**
   * 获取String值
   *
   * @param key 键
   * @return value 值
   */
  public static synchronized String get(String key) {
    Jedis jedis = getJedis();
    if (null == jedis) {
      return null;
    }
    String value = jedis.get(key);
    jedis.close();
    return value;
  }

  /**
   * 获取byte[]值
   *
   * @param key 键
   * @return value 值
   */
  public static synchronized byte[] get(byte[] key) {
    Jedis jedis = getJedis();
    if (null == jedis) {
      return null;
    }
    byte[] value = jedis.get(key);
    jedis.close();
    return value;
  }

  /**
   * 删除值
   *
   * @param key 键
   */
  public static synchronized void remove(String key) {
    try {
      Jedis jedis = getJedis();
      jedis.del(key);
      jedis.close();
    } catch (Exception e) {
      log.error("Remove keyex error : " + e);
    }
  }

  /**
   * 删除值
   *
   * @param key 键
   */
  public static synchronized void remove(byte[] key) {
    try {
      Jedis jedis = getJedis();
      jedis.del(key);
      jedis.close();
    } catch (Exception e) {
      log.error("Remove keyex error : " + e);
    }
  }

  /**
   * lpush 逆序插入
   *
   * @param key 键
   * @param strings 多个key
   */
  public static synchronized void lpush(String key, String... strings) {
    try {
      Jedis jedis = RedisUtil.getJedis();
      jedis.lpush(key, strings);
      jedis.close();
    } catch (Exception e) {
      log.error("lpush error : " + e);
    }
  }

  /**
   * lrem
   *
   * @param key 键
   * @param count 数量
   * @param value 值
   */
  public static synchronized void lrem(String key, long count, String value) {
    try {
      Jedis jedis = RedisUtil.getJedis();
      jedis.lrem(key, count, value);
      jedis.close();
    } catch (Exception e) {
      log.error("lpush error : " + e);
    }
  }

  /**
   * sadd
   *
   * @param key 键
   * @param value 值
   * @param seconds 时间
   */
  public static synchronized void sadd(String key, String value, int seconds) {
    try {
      Jedis jedis = RedisUtil.getJedis();
      jedis.sadd(key, value);
      jedis.expire(key, seconds);
      jedis.close();
    } catch (Exception e) {
      log.error("sadd error : " + e);
    }
  }

  /**
   * incr
   *
   * @param key 键
   * @return value 值
   */
  public static synchronized Long incr(String key) {
    Jedis jedis = getJedis();
    if (null == jedis) {
      return null;
    }
    long value = jedis.incr(key);
    jedis.close();
    return value;
  }

  /**
   * decr
   *
   * @param key 键
   * @return value 值
   */
  public static synchronized Long decr(String key) {
    Jedis jedis = getJedis();
    if (null == jedis) {
      return null;
    }
    long value = jedis.decr(key);
    jedis.close();
    return value;
  }
}
