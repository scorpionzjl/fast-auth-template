package com.chachae.controller;

import com.chachae.core.bean.Result;
import com.chachae.core.entity.bo.Department;
import com.chachae.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
  public Result add(Department department) {
    this.departmentService.add(department);
    return Result.ok();
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    this.departmentService.deleteByPrimaryKey(id);
    return Result.ok();
  }

  @PutMapping("/update")
  public Result update(Department department) {
    this.departmentService.update(department);
    return Result.ok();
  }
}
