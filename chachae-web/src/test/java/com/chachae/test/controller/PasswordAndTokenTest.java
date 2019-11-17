package com.chachae.test.controller;

import com.chachae.core.constant.CommonConsts;
import com.chachae.core.enums.REnum;
import com.chachae.core.utils.JwtUtil;
import com.chachae.core.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author chachae
 * @date 2019/11/16 15:20
 */
@Slf4j
public class PasswordAndTokenTest {

  @Test
  public void test() {
    String encode = Md5Util.encode("123", CommonConsts.DEFAULT_SALT);
    System.out.println("加密后的密码：" + encode);
    String token = JwtUtil.sign("1", "admin", encode);
    System.out.println("token : "+token);
    // 1s
    // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzM5NjY0NjMsInV1aWQiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiJ9.5igv65Uy8MK5Rt7cnR_bYadtSKhMi78ktIThML6R0mo
    // 测试过期
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    boolean verifyFlag = JwtUtil.verify(token, encode);
    System.out.println("token是否验证通过：" + verifyFlag);
    String username = JwtUtil.getAttribute(token, "username");
    System.out.println("username: " + username);
    System.out.println(token);
  }

  @Test
  public void testREnum() {
    Integer val = REnum.SUCCESS.val;
    String desc = REnum.SUCCESS.desc;
    System.out.println(val + " " + desc);
  }
}
