package com.chachae.entity.dto;

import com.chachae.entity.bo.Department;
import com.chachae.utils.BeanCopyUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author chachae
 * @date 2019/11/14 13:08
 */
public class DeptLevelDTO extends Department {

  private List<DeptLevelDTO> deptList = Lists.newArrayList();

  public static DeptLevelDTO adapt(Department dept) {
    return BeanCopyUtil.copyObject(dept, DeptLevelDTO.class);
  }
}
