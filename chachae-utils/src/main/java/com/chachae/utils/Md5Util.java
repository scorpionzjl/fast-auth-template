package com.chachae.utils;

import com.chachae.common.CommonConsts;

import java.security.MessageDigest;

/**
 * @author chachae
 * @date 2019/11/14 21:06
 */
public class Md5Util {

  /**
   * 密码加密
   *
   * @param password 明文密码
   * @param salt 加密盐
   * @return 密文
   */
  public static String encode(String password, String salt) {
    password = password + salt;
    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    char[] charArray = password.toCharArray();
    byte[] byteArray = new byte[charArray.length];
    for (int i = 0; i < charArray.length; i++) {
      byteArray[i] = (byte) charArray[i];
    }
    byte[] md5Bytes = md5.digest(byteArray);
    StringBuilder hexValue = new StringBuilder();
    for (byte md5Byte : md5Bytes) {
      int val = ((int) md5Byte) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();
  }

  /**
   * 密码加密使用固定加密盐
   *
   * @param password 明文密码
   * @return 密文
   */
  public String encode(String password) {
    return encode(password, CommonConsts.DEFAULT_SALT);
  }
}
