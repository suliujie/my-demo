package slj;

import java.io.Serializable;
import java.util.Date;
import work.myfavs.framework.orm.meta.annotation.Column;
import work.myfavs.framework.orm.meta.annotation.PrimaryKey;

/**
 * @author suliujie
 * @since 2022-02-16 13:01
 */
public class TenantDTO implements Serializable {

  @Column
  @PrimaryKey
  private         Long id;
  @Column private Date created;
  @Column private Date modified;
  @Column private     String tenant;

  @Column("jdbc_class")
  private String jdbcClass;

  @Column("jdbc_url")
  private String jdbcUrl;

  @Column("jdbc_user")
  private String jdbcUser;

  @Column("jdbc_password")
  private String jdbcPassword;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getJdbcClass() {
    return jdbcClass;
  }

  public void setJdbcClass(String jdbcClass) {
    this.jdbcClass = jdbcClass;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public String getJdbcUser() {
    return jdbcUser;
  }

  public void setJdbcUser(String jdbcUser) {
    this.jdbcUser = jdbcUser;
  }

  public String getJdbcPassword() {
    return jdbcPassword;
  }

  public void setJdbcPassword(String jdbcPassword) {
    this.jdbcPassword = jdbcPassword;
  }
}
