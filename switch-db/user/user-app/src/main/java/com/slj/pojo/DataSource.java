package com.slj.pojo;

import com.slj.pojo.DataSource.META.COLUMS;
import com.slj.pojo.User.META;
import work.myfavs.framework.orm.meta.annotation.Column;
import work.myfavs.framework.orm.meta.annotation.Table;
import work.myfavs.framework.orm.meta.enumeration.GenerationType;


/**
 * @author ysw
 */
@Table(value = META.TABLE, strategy = GenerationType.SNOW_FLAKE)
public class DataSource {
    @Column(value = COLUMS.datasourceId)
    String datasourceId;
    @Column(value = COLUMS.url)
    String url;
    @Column(value = COLUMS.userName)
    String userName;
    @Column(value = COLUMS.password)
    String passWord;
    @Column(value = COLUMS.code)
    String code;
    @Column
    String databasetype;

    public enum META {
        ;
        public static final String TABLE ="databasesource";

        public interface COLUMS{
            String datasourceId="datasource_id";
            String url="url";
            String userName="user_name";
            String password="password";
            String code="code";
            String databasetype="databasetype";
        }
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDatabasetype() {
        return databasetype;
    }

    public void setDatabasetype(String databasetype) {
        this.databasetype = databasetype;
    }
}
