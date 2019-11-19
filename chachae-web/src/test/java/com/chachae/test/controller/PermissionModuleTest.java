package com.chachae.test.controller;

import cn.hutool.core.lang.Console;
import com.chachae.StartApplication;
import com.chachae.common.core.entity.bo.Permission;
import com.chachae.common.redis.constant.RedisConsts;
import com.chachae.common.redis.utils.JsonUtil;
import com.chachae.common.redis.utils.RedisUtil;
import com.chachae.common.security.service.AuthService;
import com.chachae.service.PermissionService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author chachae
 * @date 2019/11/15 10:54
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class PermissionModuleTest {

  @Resource private PermissionService permissionService;
  @Resource private AuthService authService;

  @Test
  public void getAuthByRoleId() {
    List<Permission> list = this.permissionService.queryByUuid("1");
    list.forEach(Console::log);
  }

  @Test
  public void getPermissionByUuid() {
    Set<String> set = this.authService.queryPermissionsByUuid("d5aad8db8f244f5c94bf41c675ac239d");
    RedisUtil.set(
        RedisConsts.CACHE_PERMISSION_PREFIX + "d5aad8db8f244f5c94bf41c675ac239d",
        JsonUtil.obj2String(set),
        RedisConsts.EXPIRE_TIME);
    set.forEach(Console::log);
    String result = RedisUtil.get(RedisConsts.CACHE_PERMISSION_PREFIX + "d5aad8db8f244f5c94bf41c675ac239d");
    Set<String> resultSet = JsonUtil.string2Obj(result, new TypeReference<Set<String>>() {});
    assert resultSet != null;
    for (String s : resultSet) {
      System.out.println(s);
    }
  }
}
