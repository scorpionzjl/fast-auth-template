package com.chachae.test.controller;

import com.chachae.StartApplication;
import com.chachae.entity.bo.Department;
import com.chachae.service.DepartmentService;
import com.chachae.utils.JsonUtil;
import com.chachae.utils.LevelCalculateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/11/14 9:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class DepartmentModuleTest {

  @Autowired private DepartmentService deptService;

}
