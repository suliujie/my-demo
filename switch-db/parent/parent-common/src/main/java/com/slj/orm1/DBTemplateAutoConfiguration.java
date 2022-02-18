package com.slj.orm1;

import java.time.LocalDateTime;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.slj.orm1.ext.handler.ExtPropertiesPropertyHandler;
import com.slj.orm1.ext.handler.MoneyPropertyHandler;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.SpringConnFactory;
import work.myfavs.framework.orm.meta.handler.impls.BooleanPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.IntegerPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.LocalDateTimePropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.LongPropertyHandler;
import work.myfavs.framework.orm.meta.handler.impls.StringPropertyHandler;


//@ConditionalOnClass(DataSource.class)
//@EnableConfigurationProperties({OrmConfigProperties.class})
//@AutoConfigureAfter(DataSourceAutoConfiguration.class)
//@Configuration
public class DBTemplateAutoConfiguration {

//  @Bean
//  @ConditionalOnMissingBean
//  public ExtPropertiesPropertyHandler extPropertiesPropertyHandler() {
//    return new ExtPropertiesPropertyHandler();
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public MoneyPropertyHandler moneyPropertyHandler() {
//    return new MoneyPropertyHandler();
//  }
//
//  @Bean
//  @ConditionalOnBean(DataSource.class)
//  public DBTemplate dbTemplate(OrmConfigProperties ormConfigProperties, DataSource dataSource,
//      MoneyPropertyHandler moneyPropertyHandler, ExtPropertiesPropertyHandler extPropertiesPropertyHandler) {
//
//    return new DBTemplate.Builder().dataSource(dataSource)
//        .connectionFactory(SpringConnFactory.class)
//        .config(config -> config.setDbType(ormConfigProperties.getDbType())
//            .setBatchSize(ormConfigProperties.getBatchSize())
//            .setFetchSize(ormConfigProperties.getFetchSize())
//            .setQueryTimeout(ormConfigProperties.getQueryTimeout())
//            .setShowSql(true)
//            .setShowResult(ormConfigProperties.isShowResult())
//            .setMaxPageSize(ormConfigProperties.getMaxPageSize())
//            .setDefaultIsolation(ormConfigProperties.getDefaultIsolation())
//            .setWorkerId(9)
//            .setDataCenterId(ormConfigProperties.getDataCenterId()))
//        .mapping(mapper -> mapper.register(String.class, new StringPropertyHandler())
//            .register(Long.class, new LongPropertyHandler())
//            .register(long.class, new LongPropertyHandler(true))
//            .register(Integer.class, new IntegerPropertyHandler())
//            .register(int.class, new IntegerPropertyHandler(true))
//            .register(Boolean.class, new BooleanPropertyHandler())
//            .register(boolean.class, new BooleanPropertyHandler(true))
//            .register(LocalDateTime.class, new LocalDateTimePropertyHandler())
//            .register(Money.class,
//                moneyPropertyHandler)
//            .register(ExtProperties.class,
//                extPropertiesPropertyHandler))
//
//        .build();
//  }

}