package com.chachae.security.config;

import cn.hutool.core.util.StrUtil;
import com.chachae.core.constant.CommonConsts;
import com.chachae.core.utils.JwtUtil;
import com.chachae.core.utils.Md5Util;
import com.chachae.security.jwt.JwtToken;
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
public class CredentialsMatcher extends SimpleCredentialsMatcher {

  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    JwtToken jwtToken = (JwtToken) token;
    String accountCredentials = (String) getCredentials(info);
    if (StrUtil.isNotBlank(jwtToken.getPassword())) {
      // 明文密码加密
      String tokenCredentials = Md5Util.encode(jwtToken.getPassword(), CommonConsts.DEFAULT_SALT);
      if (!accountCredentials.equals(tokenCredentials)) {
        throw new DisabledAccountException("密码不正确！");
      }
    } else {
      if (!JwtUtil.verify(jwtToken.getToken(), accountCredentials)) {
        throw new DisabledAccountException("身份认证已失效，请重新登录！");
      }
    }
    return true;
  }
}
