package com.chachae.security.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.chachae.core.entity.bo.User;
import com.chachae.core.enums.REnum;
import com.chachae.core.utils.JwtUtil;
import com.chachae.security.jwt.JwtToken;
import com.chachae.security.service.AuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Jwt 鉴权模块
 *
 * @author chachae
 * @date 2019/11/14 20:45
 */
public class JwtRealm extends AuthorizingRealm {

  @Resource private AuthService authService;

  /**
   * 将默认token 改 JwtToken
   *
   * @param token 默认shiro 的token
   * @return JwtToken
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  /**
   * 授权
   *
   * @param principalCollection 认证信息集合对象
   * @return 用户授权信息
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    System.out.println("realm 鉴权");
    JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();
    SimpleAuthorizationInfo az = new SimpleAuthorizationInfo();
    // 设置角色和权限
    az.addRole(authService.queryRoleByUuid(jwtToken.getUuid()).toString());
    az.setStringPermissions(authService.queryPermissionsByUuid(jwtToken.getUuid()));
    return az;
  }

  /**
   * 认证
   *
   * @param authcToken token
   * @return 认证对象
   * @throws AuthenticationException 认证失败异常
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
      throws AuthenticationException {
    // 获取请求头中的 JwtToken
    JwtToken jwtToken = (JwtToken) authcToken;
    String username =
        StrUtil.isNotBlank(jwtToken.getUsername())
            ? jwtToken.getUsername()
            : JwtUtil.getAttribute(jwtToken.getToken(), "username");
    User user = authService.queryUserUsername(username);
    if (ObjectUtil.isEmpty(user)) {
      throw new DisabledAccountException(REnum.NOT_EXIST.desc);
    } else {
      jwtToken.setUuid(user.getUuid());
      if (StrUtil.isBlank(jwtToken.getUsername())) {
        jwtToken.setUsername(user.getUsername());
      }
      if (StrUtil.isBlank(jwtToken.getToken())) {
        jwtToken.setToken(JwtUtil.sign(user.getUuid(), user.getUsername(), user.getPassword()));
      }
      return new SimpleAuthenticationInfo(jwtToken, user.getPassword(), getName());
    }
  }
}
