package com.chachae.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/11/14 21:22
 */
@Data
@NoArgsConstructor
@Table(name = "t_permission")
public class Permission implements Serializable {

  @Id private Integer id;

  private Integer available;

  /** 名称. */
  private String name;

  /** 资源类型，[menu|button] */
  @Column(name = "resource_type")
  private String resourceType;

  /** 资源路径. */
  private String url;

  /** 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view */
  private String permission;
}
