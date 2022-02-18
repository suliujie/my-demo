package com.slj.orm1;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import work.myfavs.framework.orm.meta.pagination.IPageable;

@ApiModel(description = "分页对象")
public class QueryPage
    implements IPageable {

  @ApiModelProperty(notes = "是否分页")
  private boolean enablePage    = true;
  @ApiModelProperty(notes = "当前页码")
  private int     currentPage   = 1;
  @ApiModelProperty(notes = "每页条数")
  private int     pageSize      = 20;
  @ApiModelProperty(notes = "排序字段")
  private String  orderByField;
  @ApiModelProperty(notes = "升降序")
  private OrderBy orderByMethod = OrderBy.DESCEND;

  public String orderBy() {
    if (StrUtil.isEmpty(orderByField)) {
      return null;
    }
    return StrUtil.toUnderlineCase(this.orderByField) + StrUtil.SPACE + this.orderByMethod.getValue();
  }

  public String getOrderByField() {
    return orderByField;
  }

  public OrderBy getOrderByMethod() {
    return orderByMethod;
  }

  @Override
  public boolean getEnablePage() {

    return enablePage;
  }

  public void setEnablePage(boolean enablePage) {

    this.enablePage = enablePage;
  }

  @Override
  public int getCurrentPage() {

    return currentPage;
  }

  public void setCurrentPage(int currentPage) {

    this.currentPage = currentPage;
  }

  @Override
  public int getPageSize() {

    return pageSize;
  }

  public void setPageSize(int pageSize) {

    this.pageSize = pageSize;
  }

  public void setOrderByField(String orderByField) {
    this.orderByField = orderByField;
  }

  public void setOrderByMethod(OrderBy orderByMethod) {
    this.orderByMethod = orderByMethod;
  }
}