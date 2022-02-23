package com.slj;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import slj.UserDTO;

/**
 * @author ysw
 */
@RestController
@RequestMapping("/")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private TestService service;

    @ApiOperation(value = "批量查询单据类型")
    @GetMapping("/test")
    public void  test() throws Exception {
        LOG.info("开始批量查询用户");
        List<UserDTO> users =service.list();
        LOG.info("结果:{}",users);
        //return "OK";
    }


    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    //@Scheduled(cron = "")
    public void update(@Valid @RequestBody UserDTO dto){

        service.updateUser(dto);
    }

    /**
     * 嵌套rabbitmq方式 切换数据源
     * @param dto
     */

    @ApiOperation(value = "更新用户")
    @PostMapping("/update2")
    //@Scheduled(cron = "")
    public void update2(@Valid @RequestBody UserDTO dto){

        service.up(dto);
    }

    /**
     * rabbitmq里嵌套rpc方式  切换数据源
     * @param dto
     */
    @ApiOperation(value = "更新用户单据")
    @PostMapping("/update3")
    //@Scheduled(cron = "")
    public void update3(@Valid @RequestBody OrderDTO dto){

        service.up2(dto);
    }

}
