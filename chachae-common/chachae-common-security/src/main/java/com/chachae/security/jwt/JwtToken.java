package com.chachae.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/11/14 21:10
 */
@Data
@NoArgsConstructor
public class JwtToken implements AuthenticationToken, Serializable {

  /** token */
  private String token;

  /** uuid */
  private String uuid;

  /** 帐号 */
  private String username;

  /** 密码 */
  private String password;

  @Override
  public Object getPrincipal() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return null;
  }
}
