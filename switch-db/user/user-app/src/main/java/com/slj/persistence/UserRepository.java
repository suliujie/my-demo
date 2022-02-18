package com.slj.persistence;

import com.slj.orm1.BaseRepository;
import com.slj.pojo.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.meta.clause.Cond;
import work.myfavs.framework.orm.meta.clause.Sql;

/**
 * Brand Repository
 * PS: 此文件通过代码生成器生成
 */
@Repository
public class UserRepository
    extends BaseRepository<User> {
private Logger log =LoggerFactory.getLogger(UserRepository.class);
  /**
   * 构造方法
   *
   * @param dbTemplate DBTemplate
   */
  @Autowired
  public UserRepository(@Qualifier("dbTemplate") DBTemplate dbTemplate) {

    super(dbTemplate);
  }
  public List<User> findall(){
    //log.info(dbTemplate.getDsName());
    return super.find(Sql.SelectAll().from("user"));
  }

  public User getByCloud(String name) {
    //log.info(dbTemplate.getDsName()+this.toString());
    Sql sql=Sql.SelectAll().from("user");
    sql.where().and(Cond.eq("user_name",name,false));
    User user=super.get(User.class,sql);
    if(user==null){
      log.info("空指针:"+dbTemplate.getDsName()+ "；"+dbTemplate.getDbConfig().toString()+" ;"+name);
    }else{
      log.info(dbTemplate.getDsName()+name+"查询成功");
    }
    return user;
  }

  /**
   * 根据编码判断是否存在
   *
   * @param code
   * @return
   */
//  public boolean existsBy(String code) {
//
//    return super.existsByCond(Cond.eq("code", code));
//  }

//  public List<Long> findIdsByCodes(Collection<String> codes) {
//    return super.find(Long.class, Sql.Select("id").from(META.TABLE).where().and(Cond.in("code", codes)));
//  }
}