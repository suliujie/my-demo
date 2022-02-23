package com.slj.business;//package com.slj.com.slj.business;
//
//import com.slj.DynamicDataSource;
//import com.slj.domain.entity.Tenant;
//import com.slj.repository.repo.TenantRepository;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import work.myfavs.framework.orm.com.slj.business.BaseService;
//import work.myfavs.framework.orm.meta.clause.Sql;
//import work.myfavs.framework.orm.meta.pagination.Page;
//
//@Service
//public class TenantService extends BaseService {
//  private final TenantRepository  tenantRepository;
//  private final DynamicDataSource dynamicDataSource;
//
//  public TenantService(TenantRepository tenantRepository, @Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
//    this.tenantRepository = tenantRepository;
//    this.dynamicDataSource = dynamicDataSource;
//  }
//
//  public Tenant getByTenant(String tenant) {
//    return tenantRepository.getByField("tenant", tenant);
//  }
//
//  @Transactional(rollbackFor = Exception.class)
//  public Tenant saveTenant(Tenant tenant) {
////    Sql sql=new Sql();
////    sql.appendLine("CREATE DATABASE IF NOT EXISTS "+tenant.getTenant()+" DEFAULT CHARSET utf8 COLLATE utf8_general_ci;");
//
////    List<String> pa =new ArrayList<>();
////    pa.add(tenant.getTenant());
////    sql.setParams(pa);
//    tenantRepository.create(tenant);
//    //tenantRepository.execute(sql);
//    dynamicDataSource.addDataSource(tenant);
//    return tenant;
//  }
//
//  public Page<Tenant> findByPage() {
//    return tenantRepository.findPage(Tenant.class, new Sql("SELECT * FROM tenant"), true, 1, 10);
//  }
//}
