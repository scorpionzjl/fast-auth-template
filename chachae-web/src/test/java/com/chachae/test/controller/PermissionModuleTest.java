package com.chachae.test.controller;

import cn.hutool.core.lang.Console;
import com.chachae.StartApplication;
import com.chachae.entity.bo.Permission;
import com.chachae.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/15 10:54
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class PermissionModuleTest {

  @Resource private PermissionService permissionService;

  @Test
  public void getAuthByRoleId() {
    List<Permission> list = this.permissionService.queryByRoleId(1);
    list.forEach(Console::log);
  }
}
