package com.chachae.security.jwt;

import lombok.Builder;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author chachae
 * @date 2019/11/14 21:10
 */
@Data
@Builder
public class JwtToken implements AuthenticationToken {

  /** token */
  private String token;

  /** id */
  private Integer id;

  /** 帐号 */
  private String username;

  /** 密码 */
  private String password;

  /** 昵称 */
  private String name;

  @Override
  public Object getPrincipal() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return null;
  }
}
