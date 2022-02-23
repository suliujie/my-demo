//package com.slj.mq;
//
//import com.rabbitmq.client.Channel;
//import com.slj.service.impl.UserServiceImpl;
//import com.slj.switchdb.tenant.DynamicDataSourceContextHolder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//
//public class DynamicConsumer {
//
//////  @Autowired
//////  private UserServiceImpl userService;
////  private final UserServiceImpl userService;
//
//
//  private static final Logger                         logger = LoggerFactory.getLogger(DynamicConsumer.class);
//  private              SimpleMessageListenerContainer container;
//
//  public DynamicConsumer(MQContainerFactory fac, String name) throws Exception {
//    SimpleMessageListenerContainer container = fac.getObject();
//    container.setMessageListener(new MqConsumer() {
//      @Override
//      public boolean process(Message message, Channel channel,UserServiceImpl userService) {
//        logger.info("DynamicConsumer,{},{}, {},name,fac.getQueue(),new String(message.getBody())");
//        DynamicDataSourceContextHolder.setDataSource(message.getMessageProperties().getReceivedExchange());
//        userService.queryUserInfo();
//        //distributionConsumerMsg(message, channel);
//        return true;
//      }
//    });
//    this.container = container;
//  }
//
//  //启动消费者监听
//  public void start() {
//    container.start();
//  }
//
//  //消费者停止监听
//  public void stop() {
//    container.stop();
//  }
//
//  //消费者重启
//  public void shutdown() {
//    container.shutdown();
//  }
//
//
//  /**
//   * 用户扩展处理消息
//   */
//  public void distributionConsumerMsg(Message message, Channel channel) {
//    String ex=message.getMessageProperties().getReceivedExchange()+"user";
//    //UserDTO userDTO= JSONObject.parseObject(message.getBody(),UserDTO.class);
//    DynamicDataSourceContextHolder.setDataSource(ex);
//    //userService.queryUserInfo();
//
//  }
//
//}