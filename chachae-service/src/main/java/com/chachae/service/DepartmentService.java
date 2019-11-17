package com.chachae.service;

import com.chachae.core.entity.bo.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 9:08
 */
public interface DepartmentService {

  /**
   * 获取全部部门
   *
   * @return 所有部门的信息
   */
  List<Department> queryAll();

  /**
   * 查询所有父部门信息
   *
   * @param parentId 父部门id
   * @return 父部门信息
   */
  List<Department> queryTree(Integer parentId);

  /**
   * 模糊搜索
   *
   * @param name 部门名
   * @return 部门信息
   */
  List<Department> fuzzyQuery(String name);

  /**
   * 删除部门
   *
   * @param id 部门id
   */
  void deleteByPrimaryKey(Integer id);

  /**
   * 增加部门
   *
   * @param department 新增的部门信息
   */
  @Transactional(rollbackFor = Exception.class)
  void add(Department department);

  /**
   * 更新部门信息
   *
   * @param department 更新的信息
   */
  @Transactional(rollbackFor = Exception.class)
  void update(Department department);
}
