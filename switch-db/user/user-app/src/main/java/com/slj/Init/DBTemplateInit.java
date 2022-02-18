package com.slj.Init;//package com.slj.Init;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.slj.orm1.ExtProperties;
//import com.slj.orm1.Money;
//import com.slj.orm1.OrmConfigProperties;
//import com.slj.orm1.ext.handler.ExtPropertiesPropertyHandler;
//import com.slj.orm1.ext.handler.MoneyPropertyHandler;
//import com.slj.com.slj.pojo.DataSource;
//import com.slj.service.impl.DBChangeServiceImpl;
//import java.time.LocalDateTime;
//import java.util.List;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import work.myfavs.framework.orm.DBTemplate;
//import work.myfavs.framework.orm.DBTemplateContext;
//import work.myfavs.framework.orm.SpringConnFactory;
//import work.myfavs.framework.orm.meta.handler.impls.BooleanPropertyHandler;
//import work.myfavs.framework.orm.meta.handler.impls.IntegerPropertyHandler;
//import work.myfavs.framework.orm.meta.handler.impls.LocalDateTimePropertyHandler;
//import work.myfavs.framework.orm.meta.handler.impls.LongPropertyHandler;
//import work.myfavs.framework.orm.meta.handler.impls.StringPropertyHandler;
//
///**
// * @author suliujie
// * @since 2022-01-07 13:53
// */
//@Component
//@Order(2)
//public class DBTemplateInit implements CommandLineRunner {
//
//  private final DBChangeServiceImpl dbChangeService;
//  private final OrmConfigProperties ormConfigProperties;
//  private final MoneyPropertyHandler moneyPropertyHandler;
//  private final ExtPropertiesPropertyHandler extPropertiesPropertyHandler;
//
//  public DBTemplateInit(DBChangeServiceImpl dbChangeService, OrmConfigProperties ormConfigProperties,
//      MoneyPropertyHandler moneyPropertyHandler,
//      ExtPropertiesPropertyHandler extPropertiesPropertyHandler) {this.dbChangeService              = dbChangeService;
//                                                                  this.ormConfigProperties          = ormConfigProperties;
//                                                                  this.moneyPropertyHandler         = moneyPropertyHandler;
//                                                                  this.extPropertiesPropertyHandler = extPropertiesPropertyHandler;
//  }
//
//
//  @Override
//  public void run(String... args) throws Exception {
//
//    //createDBTemplate(ormConfigProperties,moneyPropertyHandler,extPropertiesPropertyHandler);
//  }
//
////  public void createDBTemplate(OrmConfigProperties ormConfigProperties,
////      MoneyPropertyHandler moneyPropertyHandler, ExtPropertiesPropertyHandler extPropertiesPropertyHandler){
////
////    List<DataSource> dataSources=dbChangeService.get();
////
////    for(DataSource dataSource:dataSources){
////      DruidDataSource dataSource1=new DruidDataSource();
////      dataSource1.setUrl(dataSource.getUrl());
////      dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
////      dataSource1.setUsername(dataSource.getUserName());
////      dataSource1.setPassword("Dev9527`");
////      DBTemplate dbTemplate=new DBTemplate.Builder(dataSource.getDatasourceId()).dataSource(dataSource1)
////          .connectionFactory(SpringConnFactory.class)
////          .config(config -> config.setDbType(ormConfigProperties.getDbType())
////              .setBatchSize(ormConfigProperties.getBatchSize())
////              .setFetchSize(ormConfigProperties.getFetchSize())
////              .setQueryTimeout(ormConfigProperties.getQueryTimeout())
////              .setShowSql(ormConfigProperties.isShowSql())
////              .setShowResult(ormConfigProperties.isShowResult())
////              .setMaxPageSize(ormConfigProperties.getMaxPageSize())
////              .setDefaultIsolation(ormConfigProperties.getDefaultIsolation())
////              .setWorkerId(9)
////              .setDataCenterId(ormConfigProperties.getDataCenterId()))
////          .mapping(mapper -> mapper.register(String.class, new StringPropertyHandler())
////              .register(Long.class, new LongPropertyHandler())
////              .register(long.class, new LongPropertyHandler(true))
////              .register(Integer.class, new IntegerPropertyHandler())
////              .register(int.class, new IntegerPropertyHandler(true))
////              .register(Boolean.class, new BooleanPropertyHandler())
////              .register(boolean.class, new BooleanPropertyHandler(true))
////              .register(LocalDateTime.class, new LocalDateTimePropertyHandler())
////              .register(Money.class,
////                  moneyPropertyHandler)
////              .register(ExtProperties.class,
////                  extPropertiesPropertyHandler))
////          .build();
////    }
////    System.out.println("fs");
////
////  }
//}
