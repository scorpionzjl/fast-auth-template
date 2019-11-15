package com.chachae.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chachae.common.CommonConsts;

import java.util.Date;

/**
 * @author chachae
 * @date 2019/11/14 21:00
 */
public class JwtUtil {

  /**
   * 校验token是否正确
   *
   * @param token token
   * @param secret 加密
   */
  public static boolean verify(String token, String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier =
          JWT.require(algorithm)
              .withClaim("username", getAttribute(token, "username"))
              .withClaim("id", getAttribute(token, "id"))
              .build();
      verifier.verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * 生成签名, 一周后过期
   *
   * @author lanjerry
   * @since 2019/9/10 15:06
   * @param id id
   * @param account 账号
   * @param secret 加密
   */
  public static String sign(Integer id, String account, String secret) {
    Date date = new Date(System.currentTimeMillis() + CommonConsts.JWT_EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withClaim("id", id)
        .withClaim("account", account)
        .withExpiresAt(date)
        .sign(algorithm);
  }

  /**
   * 获取账号
   *
   * @author lanjerry
   * @since 2019/9/10 18:54
   * @param token token
   */
  public static String getAttribute(String token, String attribute) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim(attribute).asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  public static void main(String[] args) {
    String token = sign(1, "admin", "123456");
    System.out.println("token:" + token);
    boolean verifyFlag = verify(token, "123456");
    System.out.println("token是否验证通过：" + verifyFlag);
  }
}
