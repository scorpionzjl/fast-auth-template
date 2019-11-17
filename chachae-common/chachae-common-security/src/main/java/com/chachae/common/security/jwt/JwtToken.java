package com.chachae.common.security.jwt;

import com.chachae.common.core.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author chachae
 * @date 2019/11/14 21:10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JwtToken extends BaseEntity implements AuthenticationToken {

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
