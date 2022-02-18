package com.slj.service.impl;

import com.slj.persistence.DataSourceRepository;
import com.slj.pojo.DataSource;
import com.slj.service.DBChangeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import work.myfavs.framework.orm.meta.clause.Sql;

@Service
public class DBChangeServiceImpl implements DBChangeService {

    private static final Logger LOG = LoggerFactory.getLogger(DBChangeServiceImpl.class);


    private final DataSourceRepository dataSourceRepository;
    //private DataSourceRepository dataSourceRepository;

//    @Autowired
//    private DynamicDataSource dynamicDataSource;

    public DBChangeServiceImpl(
        DataSourceRepository dataSourceRepository) {this.dataSourceRepository = dataSourceRepository;}


    @Override
    public List<DataSource> get() {
        return dataSourceRepository.find(Sql.SelectAll().from("databasesource"));
    }

    @Override
    public boolean changeDb(String dataSourceId) throws Exception {
        //默认切换到主数据源,进行整体资源的查找
        return false;
    }
}
