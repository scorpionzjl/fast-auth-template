package com.chachae.common.core.entity.dto;

import com.chachae.common.core.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author chachae
 * @date 2019/11/19 13:04
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntity {

  @NotBlank(message = "用户名不能为空")
  private String username;

  @NotBlank(message = "密码不能为空")
  private String password;
}
