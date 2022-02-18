package com.slj.controller;

import com.slj.service.DBChangeService;
import com.slj.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ysw
 */
@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private DBChangeService dbChangeService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    public String test() throws Exception {
        //List<User> users=userService.queryUserInfo();
       // LOG.info("结果:{}",users);
        userService.queryUserInfo();
        return "OK";
    }

}
