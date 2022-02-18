package com.slj.service;


import com.slj.pojo.DataSource;
import java.util.List;

/**
 * @author ysw
 */
public interface DBChangeService {

    List<DataSource> get();

    boolean changeDb(String dataSourceId) throws Exception;
}
