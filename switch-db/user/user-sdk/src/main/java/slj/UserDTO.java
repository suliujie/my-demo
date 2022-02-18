package slj;

import java.io.Serializable;

/**
 * @author suliujie
 * @since 2022-01-07 14:48
 */
public class UserDTO implements Serializable {

  private String userName;
  private String age;
  private String dataSourceId;

  public String getDataSourceId() {
    return dataSourceId;
  }

  public void setDataSourceId(String dataSourceId) {
    this.dataSourceId = dataSourceId;
  }

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

  @Override
  public String toString() {
    return "UserDTO{" +
        "userName='" + userName + '\'' +
        ", age='" + age + '\'' +
        '}';
  }
}
