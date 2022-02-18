package com.slj.orm1;

import java.sql.Connection;
import org.springframework.boot.context.properties.ConfigurationProperties;
import work.myfavs.framework.orm.meta.DbType;

@ConfigurationProperties("beex.database")
public class OrmConfigProperties {

  //数据库类型
  private String dbType = DbType.MYSQL;
  //一次批量插入数据的数量
  private int batchSize = 200;
  //查询每次抓取数据的数量
  private int fetchSize = 1000;
  //查询超时时间，单位：秒
  private int queryTimeout = 60;
  //是否显示SQL
  private boolean showSql = false;
  //是否显示查询结果
  private boolean showResult = false;
  //每页最大记录数
  private int maxPageSize = -1;
  //默认事务级别
  private int defaultIsolation = Connection.TRANSACTION_READ_COMMITTED;
  //终端ID
  private long workerId = 1L;
  //数据中心ID
  private long dataCenterId = 1L;

  public String getDbType() {

    return dbType;
  }

  public void setDbType(String dbType) {

    this.dbType = dbType;
  }

  public int getBatchSize() {

    return batchSize;
  }

  public void setBatchSize(int batchSize) {

    this.batchSize = batchSize;
  }

  public int getFetchSize() {

    return fetchSize;
  }

  public void setFetchSize(int fetchSize) {

    this.fetchSize = fetchSize;
  }

  public int getQueryTimeout() {

    return queryTimeout;
  }

  public void setQueryTimeout(int queryTimeout) {

    this.queryTimeout = queryTimeout;
  }

  public boolean isShowSql() {

    return showSql;
  }

  public void setShowSql(boolean showSql) {

    this.showSql = showSql;
  }

  public boolean isShowResult() {

    return showResult;
  }

  public void setShowResult(boolean showResult) {

    this.showResult = showResult;
  }

  public int getMaxPageSize() {

    return maxPageSize;
  }

  public void setMaxPageSize(int maxPageSize) {

    this.maxPageSize = maxPageSize;
  }

  public int getDefaultIsolation() {

    return defaultIsolation;
  }

  public void setDefaultIsolation(int defaultIsolation) {

    this.defaultIsolation = defaultIsolation;
  }

  public long getWorkerId() {

    return workerId;
  }

  public void setWorkerId(long workerId) {

    this.workerId = workerId;
  }

  public long getDataCenterId() {

    return dataCenterId;
  }

  public void setDataCenterId(long dataCenterId) {

    this.dataCenterId = dataCenterId;
  }

}
