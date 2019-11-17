package com.chachae.common.core.entity.bo;

import com.chachae.common.core.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chachae
 * @date 2019/11/14 21:20
 */
@Data
@NoArgsConstructor
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

  @Id private Integer id;

  private String role;
}
