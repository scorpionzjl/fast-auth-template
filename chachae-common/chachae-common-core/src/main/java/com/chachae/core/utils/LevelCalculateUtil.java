package com.chachae.core.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author chachae
 * @date 2019/11/14 12:57
 */
public class LevelCalculateUtil {

  private static final String SEPARATOR = ".";

  public static final String ROOT = "0";

  public static String calculateLevel(Integer parentId) {
    if (parentId == null || parentId == 0) {
      return ROOT;
    } else {
      return StrUtil.join(SEPARATOR, ROOT, parentId);
    }
  }
}
