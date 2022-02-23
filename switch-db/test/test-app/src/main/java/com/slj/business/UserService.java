package com.slj.business;//package com.slj.com.slj.business;
//
//import com.slj.repository.repo.UserRepository;
//import com.slj.switchdb.tenant.DynamicDataSourceContextHolder;
//import java.util.List;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import work.myfavs.framework.orm.com.slj.business.BaseService;
//import work.myfavs.framework.orm.meta.clause.Sql;
//
//@Service
//public class UserService extends BaseService {
//  private final UserRepository userRepository;
//  private final Logger         log= LoggerFactory.getLogger(UserService.class);
//  @DubboReference
//  private TestServiceInterface testService;
//
//  public UserService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }
//
//  @Transactional(rollbackFor = Exception.class)
//  public User saveUser(User user) {
//    userRepository.create(user);
//    return user;
//  }
//  @Transactional(rollbackFor = Exception.class)
//  public User updateUser(User user) throws Exception{
//    System.out.println("-----------------------------------------开始修改用户");
//    User user1=userRepository.getByField("id",user.getId());
//    System.out.println("-----------------------------------------user update "+user1.toString());
//    user1.setUsername(user.getUsername());
//    user1.setEmail(user.getEmail());
//    userRepository.update(user1);
//    //userRepository.create(user);
//    return user;
//  }
//
//  public Page<User> findByPage() {
//    System.out.println("------------------------------find user start --"+ DynamicDataSourceContextHolder.getDataSource());
//    Page<User> userList = userRepository.findPage(User.class, new Sql("SELECT * FROM USER"), true, 1, 10);
//  for(User user:userList.getData()){
//    System.out.println("-----------------------------user detail ------"+user.toString()+"-----------------------------------------------------------");
//  }
//    return userList;
//  }
//
//  public List<Users> findU(){
//    return testService.find1();
//  }
//}
