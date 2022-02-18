package com.slj.orm1;

public enum OrderBy {
  DESCEND("desc","降序"),
  ASCEND("asc","升序");

  private final String description;
  private final String value;

  OrderBy(String value,String description) {
    this.description = description;
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public String getValue() {
    return value;
  }
}
