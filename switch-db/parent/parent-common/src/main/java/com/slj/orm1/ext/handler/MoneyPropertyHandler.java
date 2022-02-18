package com.slj.orm1.ext.handler;

import cn.hutool.core.util.NumberUtil;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import com.slj.orm1.Money;
import work.myfavs.framework.orm.meta.handler.PropertyHandler;

/**
 * @author lirengui
 * @since 2020/3/17 17:43
 */
public class MoneyPropertyHandler
    extends PropertyHandler<Money> {

  private final DecimalFormat MONEY_FORMAT;
  private final BigDecimal    SCALE;

  public MoneyPropertyHandler() {
    SCALE        = new BigDecimal(10000);
    MONEY_FORMAT = new DecimalFormat("#.0000");
  }

  public MoneyPropertyHandler(DecimalFormat moneyFormat) {
    this(moneyFormat, new BigDecimal(10000));
  }

  public MoneyPropertyHandler(BigDecimal SCALE) {
    this(new DecimalFormat("#.0000"), SCALE);
  }

  public MoneyPropertyHandler(DecimalFormat moneyFormat, BigDecimal scale) {
    this.MONEY_FORMAT = moneyFormat;
    this.SCALE        = scale;
  }

  @Override
  public Money convert(ResultSet rs,
      String columnName,
      Class<Money> clazz)
      throws SQLException {

    long aLong = rs.getLong(columnName);
    if (rs.wasNull()) {
      return null;
    }

    return new Money(MONEY_FORMAT.format(NumberUtil.div(aLong, SCALE)));
  }

  @Override
  public void addParameter(PreparedStatement ps,
      int paramIndex,
      Money param)
      throws SQLException {

    if (param == null) {
      ps.setNull(paramIndex, Types.LONGVARCHAR);
      return;
    }

    ps.setLong(paramIndex, NumberUtil.mul(param.getAmount(), SCALE).longValue());
  }

}

