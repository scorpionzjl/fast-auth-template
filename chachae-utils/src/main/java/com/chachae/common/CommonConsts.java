package com.chachae.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 21:01
 */
public class CommonConsts {

  /** 本地IP数组 */
  List<String> LOCAL_HOST_IPs =
      new ArrayList<String>() {
        {
          add("0:0:0:0:0:0:0:1");
          add("localhost");
        }
      };

  /** 本地IP */
  public static final String LOCAL_HOST_IP = "127.0.0.1";

  /** JWT过期时间一周 */
  public static final long JWT_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

  /** 默认加密盐 */
  public static final String DEFAULT_SALT = "chachae";

  /** 默认管理员角色 */
  public static final String DEFAULT_ADMIN_ROLE = "admin";

  /** 默认管理员权限 */
  public static final String DEFAULT_ADMIN_PERMISSION = "*:*:*";
}
