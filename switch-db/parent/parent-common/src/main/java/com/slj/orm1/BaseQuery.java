package com.slj.orm1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.meta.Record;
import work.myfavs.framework.orm.repository.Query;

/**
 * Query 基类
 * PS: 此文件通过代码生成器生成
 */
@Repository
@Transactional(readOnly = true)
public class BaseQuery
    extends Query {

  private final static Map<Class<? extends Enum<?>>, Record> ENUM_RECORD_MAP = new ConcurrentHashMap<>();


  /**
   * 构造方法
   *
   * @param dbTemplate DBTemplate
   */
  public BaseQuery(DBTemplate dbTemplate) {

    super(dbTemplate);
  }

  /**
   * 把枚举转换为Record
   *
   * @param clazz 枚举类
   *
   * @return Record
   */
//  public Record getRecord(Class<? extends Enum<?>> clazz) {
//
//    return ENUM_RECORD_MAP.computeIfAbsent(clazz, clz -> {
//      final Record record = new Record();
//      for (Enum<?> e : clazz.getEnumConstants()) {
//        record.put(e.name(), i18n.getMessage(StrUtil.format("{}.{}", clazz.getName(), e.name())));
//      }
//      return record;
//    });
//  }

}