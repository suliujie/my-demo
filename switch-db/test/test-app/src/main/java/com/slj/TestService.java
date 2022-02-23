package com.slj;

import com.slj.mq.MQConstant;
import java.util.List;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slj.UserDTO;
import slj.UserService;

/**
 * @author suliujie
 * @since 2022-01-07 14:45
 */
@Service
public class TestService {

  @DubboReference
  private UserService    userService;
  @Autowired
  private RabbitTemplate template;
  private final Logger log= LoggerFactory.getLogger(TestService.class);

  public List<UserDTO> list(){
    return userService.queryUserInfo();
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateUser(UserDTO dto) {
    log.info("开始更新用户");
    userService.update(dto);
    log.info("更新用户成功");
  }

  @Transactional(rollbackFor = Exception.class)
  public void up(UserDTO dto){
    log.info("开始发送mq");
    template.convertAndSend(MQConstant.EXCHANGE, MQConstant.TEST_USER_UPDATE_ROUTING_KEY, dto);
    log.info("mq发送成功");

  }

  @Transactional(rollbackFor = Exception.class)
  public void up2(OrderDTO dto){
    log.info("开始发送mq");
    template.convertAndSend(MQConstant.EXCHANGE, MQConstant.TEST_USER_ORDER_UPDATE_ROUTING_KEY, dto);
    log.info("mq发送成功");

  }
}
