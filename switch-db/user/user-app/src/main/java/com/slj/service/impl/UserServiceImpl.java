package com.slj.service.impl;


import com.alibaba.druid.pool.DruidDataSource;
import com.slj.persistence.UserRepository;
import com.slj.pojo.Tenant;
import com.slj.pojo.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slj.TenantDTO;
import slj.UserDTO;
import slj.UserService;
import work.myfavs.framework.orm.ConnFactory;
import work.myfavs.framework.orm.DB;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.JdbcConnFactory;
import work.myfavs.framework.orm.meta.DbType;
import work.myfavs.framework.orm.meta.clause.Sql;
import work.myfavs.framework.orm.meta.handler.impls.BigDecimalPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.BooleanPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.DatePropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.IntegerPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.LongPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.StringPropertyHandler;

@Service
@DubboService
public class UserServiceImpl implements UserService {

    //private final Dbdd dbdd;

  private final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
  @Resource(name = "primaryDataSource")
  private DruidDataSource primaryDataSource;
    public UserServiceImpl(UserRepository repository) {

      this.repository = repository;
                                                                 }
    private final UserRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> queryUserInfo(){
        List<User> users =repository.findall();
        List<UserDTO> userDTOS=new ArrayList<>();
        for(User user:users){
          UserDTO userDTO=new UserDTO();
          userDTO.setUserName(user.getUserName());
          userDTO.setAge(user.getAge());
          userDTOS.add(userDTO);
        }
        log.info(users.toString());
        return userDTOS;
    }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(UserDTO dto) {
      //log.info("更新用户 连接数据源："+dto.getDataSourceId()+"--------------"+dbTemplate.getDsName()+dto.toString());
    User user=repository.getByCloud(dto.getUserName());
    user.setAge(dto.getAge());
    log.info("更新用户成功");
    repository.update(user);
    List<UserDTO> use=queryUserInfo();
    log.info("更新结果："+use);
  }

  @Override
  public List<TenantDTO> list() {
    DBTemplate   dbTemplate = this.buildDbTemplate(primaryDataSource, JdbcConnFactory.class);
    DB           db         = DB.conn(dbTemplate);
    List<Tenant> tenants    = db.find(Tenant.class, new Sql("SELECT * FROM tenant"));
    List<TenantDTO> dtos=new ArrayList<>();
    for(Tenant t:tenants){
      TenantDTO tenantDTO=new TenantDTO();
      tenantDTO.setTenant(t.getTenant());
      tenantDTO.setCreated(t.getCreated());
      tenantDTO.setJdbcUser(t.getJdbcUser());
      tenantDTO.setJdbcClass(t.getJdbcClass());
      tenantDTO.setJdbcPassword(t.getJdbcPassword());
      tenantDTO.setJdbcUrl(t.getJdbcUrl());
      tenantDTO.setModified(t.getModified());
      dtos.add(tenantDTO);
    }
    return dtos;
    //List<Tenant> tenants=
  }

  private DBTemplate buildDbTemplate(
      DataSource dataSource, Class<? extends ConnFactory> connectionFactory) {

    return new DBTemplate.Builder()
        .dataSource(dataSource)
        .connectionFactory(connectionFactory)
        .config(
            config ->
                config
                    .setDbType(DbType.MYSQL)
                    .setBatchSize(200)
                    .setFetchSize(100)
                    .setQueryTimeout(120)
                    .setDataCenterId(1L)
                    .setWorkerId(1L)
                    .setPageDataField("list")
                    .setPageCurrentField("pageNumber")
                    .setPageSizeField("pageSize")
                    .setPageTotalPageField("totalPage")
                    .setPageTotalRecordField("totalRow")
                    .setPageHasNextField("next"))
        .mapping(
            mapper ->
                mapper
                    .register(String.class, new StringPropertyHandler())
                    .register(BigDecimal.class, new BigDecimalPropertyHandler())
                    .register(Long.class, new LongPropertyHandler())
                    .register(long.class, new LongPropertyHandler(true))
                    .register(Boolean.class, new BooleanPropertyHandler())
                    .register(int.class, new IntegerPropertyHandler(true))
                    .register(Date.class, new DatePropertyHandler()))
        .build();
  }
}
