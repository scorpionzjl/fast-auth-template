package com.chachae.controller;

import com.chachae.common.Result;
import com.chachae.entity.bo.Department;
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
    return Result.success("获取成功", list);
  }

  @GetMapping("/query/{parentId}")
  public Result treeList(@PathVariable Integer parentId) {
    List<Department> list = this.departmentService.queryTree(parentId);
    return Result.success("获取成功", list);
  }

  @GetMapping("/query")
  public Result fuzzyList(String name) {
    List<Department> list = this.departmentService.fuzzyQuery(name);
    return Result.success("获取成功", list);
  }

  @PostMapping("/add")
  public Result add(Department department) {
    this.departmentService.add(department);
    return Result.success("增加成功");
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    this.departmentService.deleteByPrimaryKey(id);
    return Result.success("删除成功");
  }

  @PutMapping("/update")
  public Result update(Department department) {
    this.departmentService.update(department);
    return Result.success("更新成功");
  }
}
