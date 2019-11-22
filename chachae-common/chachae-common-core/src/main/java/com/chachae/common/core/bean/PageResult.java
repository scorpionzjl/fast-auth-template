package com.chachae.common.core.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统一分页结果集
 *
 * @author chachae
 * @date 2019/11/22 19:24
 */
@Data
@NoArgsConstructor
public class PageResult<T> {

  /** 数据总数 */
  private Long total;
  /** 总页数 */
  private Integer pages;
  /** 数据 */
  private List<T> items;

  public PageResult(Integer pages, Long total, List<T> items) {
    this.pages = pages;
    this.total = total;
    this.items = items;
  }
}
