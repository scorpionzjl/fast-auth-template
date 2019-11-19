package com.chachae.common.core.entity.bo;

import com.chachae.common.core.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chachae
 * @date 2019/11/14 8:58
 */
@Data
@NoArgsConstructor
@Table(name = "t_department")
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity {

  @Id private Integer id;

  private String name;

  @Column(name = "parent_id")
  private Integer parentId;

  private String level;

  private Integer seq;

  private String remark;

  @Column(name = "operator_username")
  private String operatorUsername;

  @Column(name = "operate_time")
  private Date operateTime;

  @Column(name = "operate_ip")
  private String operateIp;
}
