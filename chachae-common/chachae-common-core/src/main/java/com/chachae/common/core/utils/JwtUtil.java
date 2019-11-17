package com.chachae.common.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chachae.common.core.constant.CommonConsts;

import java.util.Date;

/**
 * Jwt 工具类
 *
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
              .withClaim("uuid", getAttribute(token, "uuid"))
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
   * @param uuid uuid
   * @param username 账号
   * @param secret 加密
   */
  public static String sign(String uuid, String username, String secret) {
    Date date = new Date(System.currentTimeMillis() + CommonConsts.JWT_EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withClaim("uuid", uuid)
        .withClaim("username", username)
        .withExpiresAt(date)
        .sign(algorithm);
  }

  /**
   * 获取账号
   *
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
}
