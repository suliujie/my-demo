package com.slj;

import java.io.Serializable;

/**
 * @author suliujie
 * @since 2022-02-16 10:00
 */
public class OrderDTO implements Serializable {

  private String userName;
  private String age;
  private String dataSourceId;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getDataSourceId() {
    return dataSourceId;
  }

  public void setDataSourceId(String dataSourceId) {
    this.dataSourceId = dataSourceId;
  }

  @Override
  public String toString() {
    return "OrderDTO{" +
        "userName='" + userName + '\'' +
        ", age='" + age + '\'' +
        ", dataSourceId='" + dataSourceId + '\'' +
        '}';
  }
}
