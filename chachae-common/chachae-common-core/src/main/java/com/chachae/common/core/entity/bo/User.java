package com.chachae.common.core.entity.bo;

import com.chachae.common.core.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author chachae
 * @date 2019/11/12 21:53
 */
@Data
@AllArgsConstructor
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

  @Id private String uuid;

  @NotNull(message = "用户名不能为空")
  private String username;

  private String password;

  @NotNull(message = "角色不能为空")
  private Integer role;
}
