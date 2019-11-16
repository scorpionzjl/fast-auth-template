package com.chachae.security.config;

import cn.hutool.core.util.StrUtil;
import com.chachae.common.CommonConsts;
import com.chachae.security.jwt.JwtToken;
import com.chachae.security.utils.JwtUtil;
import com.chachae.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义密码匹配器
 *
 * @author chachae
 * @date 2019/11/15 21:40
 */
@Slf4j
public class CredentialsMatcher extends SimpleCredentialsMatcher {

  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    JwtToken jwtToken = (JwtToken) token;
    String accountCredentials = (String) getCredentials(info);
    if (StrUtil.isNotBlank(jwtToken.getPassword())) {
      // 明文密码加密
      String tokenCredentials = Md5Util.encode(jwtToken.getPassword(), CommonConsts.DEFAULT_SALT);
      log.info("jwtToken.getPassword(): {}", jwtToken.getPassword());
      log.info(
          "accountCredentials: {} - tokenCredentials: {} ", accountCredentials, tokenCredentials);
      if (!accountCredentials.equals(tokenCredentials)) {
        throw new AuthenticationException("密码不正确！");
      }
    } else {
      if (!JwtUtil.verify(jwtToken.getToken(), accountCredentials)) {
        throw new DisabledAccountException("身份认证已失效，请重新登录！");
      }
    }
    return true;
  }
}
