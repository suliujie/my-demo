package com.slj.orm1.ext.handler;

import com.alibaba.fastjson.JSON;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.slj.orm1.ExtProperties;
import work.myfavs.framework.orm.meta.handler.PropertyHandler;

public class ExtPropertiesPropertyHandler
    extends PropertyHandler<ExtProperties> {

  @Override
  public ExtProperties convert(ResultSet resultSet,
      String s,
      Class<ExtProperties> aClass)
      throws SQLException {

    final String jsonStr = resultSet.getString(s);
    if (resultSet.wasNull()) {
      return null;
    }
    return JSON.parseObject(jsonStr, aClass);
  }

  @Override
  public void addParameter(PreparedStatement preparedStatement,
      int i,
      ExtProperties extProperties)
      throws SQLException {

    final String jsonStr = JSON.toJSONString(extProperties);
    preparedStatement.setString(i, jsonStr);
  }

//  public static void main(String[] args) {
//
//
//    String jsonStr = "{'key01':'value01','key02':'value02'}";
//    ExtProperties extProperties = JSONUtil.toBean(jsonStr, ExtProperties.class);
//    Object key01 = extProperties.get("key01");
//
//    ExtProperties extProperties1 = new ExtProperties();
//    extProperties1.put("key03","value03");
//    extProperties1.put("key04","value04");
//    String jsonStr1 = JSONUtil.toJsonStr(extProperties1);
//  }


}
