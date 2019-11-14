package com.chachae.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chachae
 * @date 2019/11/12 21:53
 */
@Data
@AllArgsConstructor
@Table(name = "t_user")
public class User implements Serializable {

  @Id private String uuid;

  @NotNull(message = "用户名不能为空")
  private String username;

  @NotNull(message = "密码不能为空")
  private String password;

  @NotNull(message = "角色不能为空")
  private Integer role;
}
