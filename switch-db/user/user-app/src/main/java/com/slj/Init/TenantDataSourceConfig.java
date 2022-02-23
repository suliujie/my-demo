package com.slj.Init;

import com.alibaba.druid.pool.DruidDataSource;
import com.slj.pojo.Tenant;
import com.slj.switchdb.config.DynamicDataSource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
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


@Configuration
public class TenantDataSourceConfig implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceConfig.class);

  @Resource(name = "dynamicDataSource")
  private DynamicDataSource dynamicDataSource;
  @Resource(name = "primaryDataSource")
  private DruidDataSource druidDataSource;

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
    DBTemplate dbTemplate = this.buildDbTemplate(primaryDataSource, JdbcConnFactory.class);
    DB           db      = DB.conn(dbTemplate);
    List<Tenant> tenants = db.find(Tenant.class, new Sql("SELECT * FROM tenant"));
        if(DynamicDataSource.connectProperties == null)
      DynamicDataSource.connectProperties = primaryDataSource.getConnectProperties();

    for (Tenant tenant : tenants) {
      DruidDataSource ds = new DruidDataSource();
      ds.setConnectProperties(DynamicDataSource.connectProperties);
      ds.setDriverClassName(tenant.getJdbcClass());
      ds.setUrl(tenant.getJdbcUrl());
      ds.setUsername(tenant.getJdbcUser());
      ds.setPassword(tenant.getJdbcPassword());
      ds.setRemoveAbandoned(true);
      ds.setRemoveAbandonedTimeout(10);

      // 池中某个连接的空闲时长达到 N 毫秒后, 连接池在下次检查空闲连接时，将回收该连接,要小于防火墙超时设置
      ds.setMinEvictableIdleTimeMillis(40000);

      // 程序没有close连接且空闲时长超过minEvictableIdleTimeMillis,则会执validationQuery指定的SQL,以保证该程序连接不会池kill掉,其范围不超过minIdle指定的连接个数
      // ds.setKeepAlive();

      // 回收空闲连接时，将保证至少有minIdle个连接
      // ds.setMinIdle();

      // 要求程序从池中get到连接后, N 秒后必须close,否则druid 会强制回收该连接,不管该连接中是活动还是空闲, 以防止进程不会进行close而霸占连接;	false,当发现程序有未连接,不管该连接中是活动还是空闲, 以防止进程不会进行close而霸占连接。
     //ds.setRemoveAbandoned();

      //设置druid 强制回收连接的时限，当程序从池中get到连接开始算起，超过此值后，druid将强制回收该连接，单位秒。
      //ds.setRemoveAbandonedTimeout();

      //当程序请求连接，池在分配连接时，是否先检查该连接是否有效
      //ds.setTestWhileIdle();
      // 检查空闲连接的频率，单位毫秒, 非正整数时表示不进行检查
      ds.setTimeBetweenConnectErrorMillis(40000);
      ds.setMaxWait(300000);
      ds.setRemoveAbandoned(false);
      ds.setTestWhileIdle(false);
      ds.setLoginTimeout(30);
      //每个连接最多缓存多少个SQL
      ds.setMaxPoolPreparedStatementPerConnectionSize(20);
      //检查池中的连接是否仍可用的 SQL 语句,drui会连接到数据库执行该SQL如果正常返回，则表示连接可用，否则表示连接不可用
      //ds.setValidationQuery();
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
  public DBTemplate buildDbTemplate(
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

  @Override
  public void run(String... args) throws Exception {
    initSource(druidDataSource,dynamicDataSource);
  }
}
