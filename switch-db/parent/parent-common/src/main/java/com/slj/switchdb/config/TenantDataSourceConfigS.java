package com.slj.switchdb.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import work.myfavs.framework.orm.ConnFactory;
import work.myfavs.framework.orm.DB;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.JdbcConnFactory;
import work.myfavs.framework.orm.SpringConnFactory;
import work.myfavs.framework.orm.meta.DbType;
import work.myfavs.framework.orm.meta.clause.Sql;
import work.myfavs.framework.orm.meta.handler.impls.BigDecimalPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.BooleanPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.DatePropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.IntegerPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.LongPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.StringPropertyHandler;


@Configuration
public class TenantDataSourceConfigS {
  private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceConfigS.class);


  @Bean(name = "primaryDataSource", initMethod = "init", destroyMethod = "close")
  @ConfigurationProperties("spring.datasource.primary")
  public DruidDataSource dataSource() {
    DruidDataSource datasource = DruidDataSourceBuilder.create().build();
    datasource.setDefaultAutoCommit(false);
    return datasource;
  }
//
  @Primary
  @Bean(name = "dynamicDataSource")
  public DynamicDataSource dynamicDataSource(
      @Qualifier("primaryDataSource") DruidDataSource primaryDataSource) {
    logger.info("=====????????????????????????=====");

    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    // ???????????????
    dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);

//    DBTemplate   dbTemplate = this.buildDbTemplate(primaryDataSource, JdbcConnFactory.class);
//    DB           db         = DB.conn(dbTemplate);
//    List<Tenant> tenants    = db.find(Tenant.class, new Sql("SELECT * FROM tenant"));
//
    Map<Object, Object> customDataSources = new HashMap<>();
    //customDataSources.put("")

    if(DynamicDataSource.connectProperties == null)
      DynamicDataSource.connectProperties = primaryDataSource.getConnectProperties();

//    for (Tenant tenant : tenants) {
//      DruidDataSource ds = new DruidDataSource();
//      ds.setConnectProperties(DynamicDataSource.connectProperties);
//      ds.setDriverClassName(tenant.getJdbcClass());
//      ds.setUrl(tenant.getJdbcUrl());
//      ds.setUsername(tenant.getJdbcUser());
//      ds.setPassword(tenant.getJdbcPassword());
//      customDataSources.put(tenant.getTenant(), ds);
//      logger.info("???????????????????????????" + tenant.getTenant());
//    }

    dynamicDataSource.setTargetDataSources(customDataSources);
    return dynamicDataSource;
  }

  @Bean
  @Primary
  public DataSourceTransactionManager dataSourceTransactionManager(
      @Qualifier("dynamicDataSource") DataSource dataSource) {

    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public DBTemplate dbTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
    return this.buildDbTemplate(dataSource, SpringConnFactory.class);
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
