package com.chachae.controller;

import com.chachae.common.core.bean.Result;
import com.chachae.common.core.entity.bo.Department;
import com.chachae.common.core.utils.JwtUtil;
import com.chachae.service.DepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 9:15
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {

  @Resource private DepartmentService departmentService;

  @GetMapping("/list")
  public Result list() {
    List<Department> list = this.departmentService.queryAll();
    return Result.ok(list);
  }

  @GetMapping("/query/{parentId}")
  public Result treeList(@PathVariable Integer parentId) {
    List<Department> list = this.departmentService.queryTree(parentId);
    return Result.ok(list);
  }

  @GetMapping("/query")
  public Result fuzzyList(String name) {
    List<Department> list = this.departmentService.fuzzyQuery(name);
    return Result.ok(list);
  }

  @PostMapping("/add")
  @RequiresPermissions("department:add")
  public Result add(Department department, HttpServletRequest request) {
    String username = JwtUtil.getAttribute(request, "username");
    department.setOperatorUsername(username);
    this.departmentService.add(department);
    return Result.ok();
  }

  @DeleteMapping("/delete/{id}")
  @RequiresPermissions("department:delete")
  public Result delete(@PathVariable Integer id) {
    this.departmentService.deleteByPrimaryKey(id);
    return Result.ok();
  }

  @PutMapping("/update")
  @RequiresPermissions("department:update")
  public Result update(Department department, HttpServletRequest request) {
    String username = JwtUtil.getAttribute(request, "username");
    department.setOperatorUsername(username);
    this.departmentService.update(department);
    return Result.ok();
  }
}
