package com.slj.persistence;

import com.slj.orm1.BaseRepository;
import com.slj.pojo.DataSource;
import org.springframework.stereotype.Repository;
import work.myfavs.framework.orm.DBTemplate;

/**
 * @author ysw
 */
@Repository
public class DataSourceRepository extends BaseRepository<DataSource> {


    /**
     * 构造方法
     *
     * @param dbTemplate DBTemplate
     */
    public DataSourceRepository(DBTemplate dbTemplate) {
        super(dbTemplate);
    }
}
