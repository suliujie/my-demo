package com.slj.controller;//package com.slj.controller;
//
//import java.util.List;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import work.myfavs.framework.example.com.slj.business.UserService;
//import work.myfavs.framework.example.domain.entity.User;
//import work.myfavs.framework.orm.meta.pagination.Page;
//import work.myfavs.framework.test.sdk1.entity.Users;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//  private final UserService userService;
//
//  public UserController(UserService userService) {
//    this.userService = userService;
//  }
//
//  @RequestMapping(value = "/save", method = RequestMethod.POST)
//  public ResponseEntity<Long> saveUser(RequestEntity<User> entity) {
//    User user = userService.saveUser(entity.getBody());
//    return ResponseEntity.ok().body(user.getId());
//  }
//  @RequestMapping(value = "/update", method = RequestMethod.POST)
//  public ResponseEntity<Long> updateUser(RequestEntity<User> entity) throws Exception {
//    User user = userService.updateUser(entity.getBody());
//    return ResponseEntity.ok().body(user.getId());
//  }
//
//  @RequestMapping(value = "/find-by-page")
//  public ResponseEntity<Page<User>> findByPage(){
//    return ResponseEntity.ok().body(userService.findByPage());
//  }
//
//  @RequestMapping(value = "/rpc")
//  public ResponseEntity<List<Users>> rpc(){
//    return ResponseEntity.ok().body(userService.findU());
//  }
//}
