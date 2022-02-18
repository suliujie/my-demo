package com.slj.Init;

import com.alibaba.druid.pool.DruidDataSource;
import com.slj.TenantDTO;
import com.slj.UserService;
import com.slj.switchdb.config.DynamicDataSource;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TenantDataSourceConfiga implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceConfiga.class);

  @Resource(name = "dynamicDataSource")
  private DynamicDataSource dynamicDataSource;
  @Resource(name = "primaryDataSource")
  private DruidDataSource druidDataSource;
  @DubboReference
  private UserService userService;

//  @Bean(name = "primaryDataSource", initMethod = "init", destroyMethod = "close")
//  @ConfigurationProperties("spring.datasource.primary")
//  public DruidDataSource dataSource() {
//    DruidDataSource datasource = DruidDataSourceBuilder.create().build();
//    datasource.setDefaultAutoCommit(false);
//    return datasource;
//  }

//  @Primary
//  @Bean(name = "dynamicDataSource")
//  public DynamicDataSource dynamicDataSource(
//      @Qualifier("primaryDataSource") DruidDataSource primaryDataSource) {
//    logger.info("=====初始化动态数据源=====");
//
//    DynamicDataSource dynamicDataSource = new DynamicDataSource();
//    // 默认数据源
//    dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);
//
//    DBTemplate dbTemplate = this.buildDbTemplate(primaryDataSource, JdbcConnFactory.class);
//    DB           db      = DB.conn(dbTemplate);
//    List<Tenant> tenants = db.find(Tenant.class, new Sql("SELECT * FROM tenant"));
//
//    Map<Object, Object> customDataSources = new HashMap<>();
//
//    if(DynamicDataSource.connectProperties == null)
//      DynamicDataSource.connectProperties = primaryDataSource.getConnectProperties();
//
//    for (Tenant tenant : tenants) {
//      DruidDataSource ds = new DruidDataSource();
//      ds.setConnectProperties(DynamicDataSource.connectProperties);
//      ds.setDriverClassName(tenant.getJdbcClass());
//      ds.setUrl(tenant.getJdbcUrl());
//      ds.setUsername(tenant.getJdbcUser());
//      ds.setPassword(tenant.getJdbcPassword());
//      customDataSources.put(tenant.getTenant(), ds);
//      logger.info("已加载租户库数据源" + tenant.getTenant());
//    }
//
//    dynamicDataSource.setTargetDataSources(customDataSources);
//    return dynamicDataSource;
//  }

  //@PostConstruct
  public DynamicDataSource initSource(@Qualifier("primaryDataSource") DruidDataSource primaryDataSource,
      @Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource){
    logger.info("=====初始化动态数据源=====");
    Map<Object, Object> customDataSources =dynamicDataSource.getTargetDataSources();
//    DBTemplate dbTemplate = this.buildDbTemplate(primaryDataSource, JdbcConnFactory.class);
//    DB           db      = DB.conn(dbTemplate);
//    List<Tenant> tenants = db.find(Tenant.class, new Sql("SELECT * FROM tenant"));
    List<TenantDTO> tenants =userService.list();
        if(DynamicDataSource.connectProperties == null)
      DynamicDataSource.connectProperties = primaryDataSource.getConnectProperties();

    for (TenantDTO tenant : tenants) {
      DruidDataSource ds = new DruidDataSource();
      ds.setConnectProperties(DynamicDataSource.connectProperties);
      ds.setDriverClassName(tenant.getJdbcClass());
      ds.setUrl(tenant.getJdbcUrl());
      ds.setUsername(tenant.getJdbcUser());
      ds.setPassword(tenant.getJdbcPassword());
      customDataSources.put(tenant.getTenant(), ds);
      logger.info("已加载租户库数据源" + tenant.getTenant());
    }
    dynamicDataSource.setTargetDataSources(customDataSources);
    dynamicDataSource.afterPropertiesSet();
    return dynamicDataSource;
  }

//  @Bean
//  @Primary
//  public DataSourceTransactionManager dataSourceTransactionManager(
//      @Qualifier("dynamicDataSource") DataSource dataSource) {
//
//    return new DataSourceTransactionManager(dataSource);
//  }

//  @Bean
//  public DBTemplate dbTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
//    return this.buildDbTemplate(dataSource, SpringConnFactory.class);
//  }
//
//  private DBTemplate buildDbTemplate(
//      DataSource dataSource, Class<? extends ConnFactory> connectionFactory) {
//
//    return new DBTemplate.Builder()
//        .dataSource(dataSource)
//        .connectionFactory(connectionFactory)
//        .config(
//            config ->
//                config
//                    .setDbType(DbType.MYSQL)
//                    .setBatchSize(200)
//                    .setFetchSize(100)
//                    .setQueryTimeout(120)
//                    .setDataCenterId(1L)
//                    .setWorkerId(1L)
//                    .setPageDataField("list")
//                    .setPageCurrentField("pageNumber")
//                    .setPageSizeField("pageSize")
//                    .setPageTotalPageField("totalPage")
//                    .setPageTotalRecordField("totalRow")
//                    .setPageHasNextField("next"))
//        .mapping(
//            mapper ->
//                mapper
//                    .register(String.class, new StringPropertyHandler())
//                    .register(BigDecimal.class, new BigDecimalPropertyHandler())
//                    .register(Long.class, new LongPropertyHandler())
//                    .register(long.class, new LongPropertyHandler(true))
//                    .register(Boolean.class, new BooleanPropertyHandler())
//                    .register(int.class, new IntegerPropertyHandler(true))
//                    .register(Date.class, new DatePropertyHandler()))
//        .build();
//  }

  @Override
  public void run(String... args) throws Exception {
    initSource(druidDataSource,dynamicDataSource);
  }
}
