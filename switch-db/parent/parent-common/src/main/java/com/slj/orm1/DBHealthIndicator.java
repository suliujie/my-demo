package com.slj.orm1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import work.myfavs.framework.orm.DBTemplate;

/**
 * 数据库健康指标
 *
 * @author liufuhong
 * @since 2020-05-29 16:28
 */

//@ConditionalOnBean({DBTemplate.class, DataSourceProperties.class})
//@Component
public class DBHealthIndicator
    //implements HealthIndicator
{

//  private final DBTemplate           dbTemplate;
//  private final DataSourceProperties dataSourceProperties;
//
//  public DBHealthIndicator(DBTemplate dbTemplate,
//      DataSourceProperties dataSourceProperties) {
//    this.dbTemplate           = dbTemplate;
//    this.dataSourceProperties = dataSourceProperties;
//  }
//
//  @Override
//  public Health health() {
//    if (dbTemplate == null) {
//      return Health.down().build();
//    }
//
//    try {
//      Map<String, Object> map = new HashMap<>();
//      map.put("autoCommit", dbTemplate.getDataSource().getConnection().getAutoCommit());
//      map.put("url", dataSourceProperties.getUrl());
//      map.put("name", dataSourceProperties.getName());
//      map.put("password", dataSourceProperties.getPassword());
//
//      return Health
//          .up()
//          .withDetails(map)
//          .build();
//    } catch (SQLException e) {
//      return Health.down(e).build();
//    }
//  }
}
