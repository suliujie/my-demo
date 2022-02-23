package com.slj.persistence;//package com.slj.repository.repo;

import com.slj.orm1.BaseRepository;
import com.slj.pojo.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import work.myfavs.framework.orm.DBTemplate;

@Repository
public class TenantRepository extends BaseRepository<Tenant> {
  /**
   * 构造方法
   *
   * @param dbTemplate DBTemplate
   */
  @Autowired
  public TenantRepository(DBTemplate dbTemplate) {
    super(dbTemplate);
  }
}
