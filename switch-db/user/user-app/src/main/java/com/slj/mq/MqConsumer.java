//package com.slj.mq;
//
//import com.rabbitmq.client.Channel;
//import com.slj.mq.IDynamicConsumer;
//import com.slj.service.impl.UserServiceImpl;
//import java.io.IOException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public abstract class MqConsumer implements IDynamicConsumer {
//  private volatile boolean                        end = false;
//  private          SimpleMessageListenerContainer container;
//  private          boolean                        autoAck;
//  @Autowired
//  private UserServiceImpl userService;
//
//  @Override
//  public void setContainer(SimpleMessageListenerContainer container) {
//    this.container = container;
//    autoAck = container.getAcknowledgeMode().isAutoAck();
//  }
//
//  @Override
//  public void shutdown() {
//    end = true;
//  }
//
//  protected void autoAck(Message message, Channel channel, boolean success) throws IOException {
//    if (autoAck) {
//      return;
//    }
//
//    if (success) {
//      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    } else {
//      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//    }
//  }
//  public void onMessage(Message message, Channel channel) throws Exception {
//    try {
//      autoAck(message, channel, process(message, channel,userService));
//    } catch (Exception e) {
//      autoAck(message, channel, false);
//      throw e;
//    } finally {
//      if (end) {
//        container.stop();
//      }
//    }
//  }
//
//  public abstract boolean process(Message message, Channel channel, UserServiceImpl userService);
//}